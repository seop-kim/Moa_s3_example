package com.moa.MoaS3Example.global.aws.s3.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.HeadBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.waiters.HeadObjectFunction;
import com.moa.MoaS3Example.global.aws.s3.config.BucketConfig;
import com.moa.MoaS3Example.global.aws.s3.dto.BucketDto;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class BucketService {
    private final AmazonS3 amazonS3Client;
    private final BucketConfig config;

    public BucketDto.Response upload(MultipartFile file) {
        return BucketDto.of(uploadS3(file));
    }

    public void read(String keyName) {
        // TODO
        //   s3에서 데이터를 조회하는 것은 fullPath가 아닌 keyName으로 조회를 한다.
        //   Image는 path만이 아닌 keyName도 보관을 해야한다.
        //    FIXME
        //      KeyName으로 저장하고 KeyName로 컨트롤한다.
        boolean check = amazonS3Client.doesObjectExist(config.getBucket(), keyName);
        if (!check) {
            throw new IllegalArgumentException("has not image data");
        }
    }


    private String uploadS3(MultipartFile file) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH:mm:ss"));

        log.info("Bucket Service init time :: " + time);

        String keyName = time + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(
                    new PutObjectRequest(config.getBucket(), keyName, inputStream, objectMetadata)
            );

        } catch (SdkClientException e) {
            log.info("BucketService uploadS3 SdkClientException");
            throw new SdkClientException(e);
        } catch (IOException e) {
            log.info("BucketService uploadS3 IOException");
            throw new RuntimeException(e);
        }

        log.info("Bucket Service uploadFileName : " + keyName);

        return keyName;
    }
}
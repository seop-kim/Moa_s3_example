package com.moa.MoaS3Example.global.aws.s3.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.moa.MoaS3Example.global.aws.s3.config.BucketConfig;
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

    public String upload(MultipartFile file) {
        return uploadS3(file);
    }


    private String uploadS3(MultipartFile file) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd_HH:mm:ss"));

        log.info("time :: " + time);

        String uploadFileName = time + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try (InputStream inputStream = file.getInputStream()) {
            amazonS3Client.putObject(
                    new PutObjectRequest(config.getBucket(), uploadFileName, inputStream, objectMetadata)
            );

        } catch (SdkClientException e) {
            log.info("BucketService uploadS3 SdkClientException");
            throw new SdkClientException(e);
        } catch (IOException e) {
            log.info("BucketService uploadS3 IOException");
            throw new RuntimeException(e);
        }

        return amazonS3Client.getUrl(config.getBucket(), uploadFileName).toString();
    }
}
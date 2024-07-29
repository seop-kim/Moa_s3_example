package com.moa.MoaS3Example.global.aws.s3.controller;

import com.moa.MoaS3Example.global.aws.s3.service.BucketService;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;

    @PostMapping("upload")
    public ResponseEntity<String> upload(@RequestPart MultipartFile multipartFile) throws IOException {
        log.info("BucketController init " + LocalDateTime.now());
        String fileName = bucketService.upload(multipartFile);
        return ResponseEntity.created(null).body(fileName);
    }
}

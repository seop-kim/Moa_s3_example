package com.moa.MoaS3Example.global.aws.s3.controller;

import com.moa.MoaS3Example.global.aws.s3.dto.BucketDto;
import com.moa.MoaS3Example.global.aws.s3.dto.BucketDto.Response;
import com.moa.MoaS3Example.global.aws.s3.service.BucketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Image-API")
@RequestMapping("/images")
public class BucketController {
    private final BucketService bucketService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "이미지 추가", description = "1개 이미지 저장 API", responses = {@ApiResponse(responseCode = "201")})
    public ResponseEntity<BucketDto.Response> upload(@RequestPart MultipartFile file) {
        log.info("BucketController init time :: " + LocalDateTime.now());
        Response response = bucketService.upload(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

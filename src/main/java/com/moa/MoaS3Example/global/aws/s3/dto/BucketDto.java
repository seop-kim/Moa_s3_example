package com.moa.MoaS3Example.global.aws.s3.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public record BucketDto() {
    @Builder
    public record Response(
            String url
    ) {
    }

}

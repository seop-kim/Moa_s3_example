package com.moa.MoaS3Example.global.aws.s3.dto;

import lombok.Builder;

public record BucketDto() {
    @Builder
    public record Response(
            String keyName
    ) {
    }

    public static BucketDto.Response of(String keyName) {
        return BucketDto.Response.builder().keyName(keyName).build();
    }
}

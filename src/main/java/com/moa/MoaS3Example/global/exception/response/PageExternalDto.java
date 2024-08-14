package com.moa.MoaS3Example.global.exception.response;

import lombok.Builder;

public record PageExternalDto<T>() {
    public record Response<T>(T data, PageInfo pageInfo) {
    }

    @Builder
    public record PageInfo(
            int page,
            int size,
            boolean hasNext,
            int totalSize) {
    }
}

package com.moa.MoaS3Example.global.image.dto;

import com.moa.MoaS3Example.global.image.entity.Image;
import java.time.LocalDateTime;
import lombok.Builder;

public record ImageDto(Long id) {
    @Builder
    public record Response(
            Long id,
            String originUrl,
            LocalDateTime createAt) {
    }

    public static ImageDto.Response of(Image image) {
        return ImageDto.Response.builder()
                .id(image.getId())
                .originUrl(image.getKeyName())
                .createAt(image.getCreateAt())
                .build();
    }
}

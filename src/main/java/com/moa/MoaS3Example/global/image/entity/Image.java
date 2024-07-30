package com.moa.MoaS3Example.global.image.entity;

import com.moa.MoaS3Example.global.common.entity.BaseEntity;
import com.moa.MoaS3Example.global.image.enumerated.EntityType;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Image extends BaseEntity {
    private String entityType;

    private Long entityId;

    private String originUrl;

    private String lowUrl;

    public static Image create(BaseEntity entity, String url) {
        return Image.builder()
                .entityType(EntityType.getEntityType(entity))
                .entityId(entity.getId())
                .originUrl(url)
                .build();
    }

    public void modOriginUrl(String url) {
        this.originUrl = url;
    }

    public void modLowUrl(String url) {
        this.lowUrl = url;
    }
}

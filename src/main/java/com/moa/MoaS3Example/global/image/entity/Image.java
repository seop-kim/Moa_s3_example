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

    private String keyName;


    public static Image create(BaseEntity entity, String keyName) {
        return Image.builder()
                .entityType(EntityType.getEntityType(entity))
                .entityId(entity.getId())
                .keyName(keyName)
                .build();
    }

    public void modKeyName(String keyName) {
        this.keyName = keyName;
    }

}

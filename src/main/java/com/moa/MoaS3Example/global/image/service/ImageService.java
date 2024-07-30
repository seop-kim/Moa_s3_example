package com.moa.MoaS3Example.global.image.service;

import com.moa.MoaS3Example.global.aws.s3.service.BucketService;
import com.moa.MoaS3Example.global.common.entity.BaseEntity;
import com.moa.MoaS3Example.global.image.entity.Image;
import com.moa.MoaS3Example.global.image.enumerated.EntityType;
import com.moa.MoaS3Example.global.image.repository.ImageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final BucketService bucketService;

    public Long save(BaseEntity entity, String url) {
        validateImageUrl(url);
        validateEntityId(entity);
        Image image = Image.create(entity, url);
        imageRepository.save(image);
        return image.getId();
    }

    public List<Image> findAll(BaseEntity entity) {
        return imageRepository.findAllByEntityIdAndEntityType(entity.getId(), EntityType.getEntityType(entity));
    }

    private void validateEntityId(BaseEntity entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("has not entity id");
        }
    }

    private void validateImageUrl(String url) {
        bucketService.read(url);
    }
}

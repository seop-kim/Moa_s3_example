package com.moa.MoaS3Example.global.image.repository;

import com.moa.MoaS3Example.global.image.entity.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByEntityIdAndEntityType(Long entityId, String entityType);
}

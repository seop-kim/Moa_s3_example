package com.moa.MoaS3Example.global.image.enumerated;

import com.moa.MoaS3Example.domain.board.board.entity.Board;
import com.moa.MoaS3Example.global.common.entity.BaseEntity;
import com.moa.MoaS3Example.global.image.entity.Image;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

@RequiredArgsConstructor
public enum EntityType {
    IMAGE("image", Image.builder().build()),
    BOARD("board", Board.builder().build()),
    ;

    private final String entityType;
    private final BaseEntity entityObj;


    public static String getEntityType(BaseEntity entityObj) {
        Class<?> classType = getClassType(entityObj);

        for (EntityType entity : EntityType.values()) {
            if (classType.isInstance(entity.entityObj)) {
                return entity.entityType;
            }
        }

        throw new IllegalArgumentException("no matching entity type");
    }

    private static Class<?> getClassType(BaseEntity entityObj) {
        if (entityObj instanceof HibernateProxy proxy) {
            LazyInitializer initializer = proxy.getHibernateLazyInitializer();
            return initializer.getPersistentClass();
        }

        return entityObj.getClass();
    }
}
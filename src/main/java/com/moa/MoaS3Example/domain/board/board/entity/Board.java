package com.moa.MoaS3Example.domain.board.board.entity;

import com.moa.MoaS3Example.domain.board.board.dto.AddBoardDto;
import com.moa.MoaS3Example.global.common.entity.BaseEntity;
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
public class Board extends BaseEntity {
    private String title;
    private String content;

    public static Board create(AddBoardDto.Request data) {
        return Board.builder()
                .title(data.title())
                .content(data.content())
                .build();
    }
}

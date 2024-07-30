package com.moa.MoaS3Example.domain.board.board.dto;

import com.moa.MoaS3Example.domain.board.board.entity.Board;
import com.moa.MoaS3Example.global.image.dto.ImageDto;
import java.util.List;
import lombok.Builder;

public record FindBoardDto() {
    @Builder
    public record Response(
            Long id,
            String title,
            String content,
            List<ImageDto .Response> images
    ) {}

    public static FindBoardDto.Response of(Board board, List<ImageDto .Response> images) {
        return Response.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .images(images)
                .build();
    }
}

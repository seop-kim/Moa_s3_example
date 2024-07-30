package com.moa.MoaS3Example.domain.board.board.dto;

import java.util.List;
import lombok.Builder;

public record AddBoardDto() {
    public record Request(
            String title,
            String content,
            List<String> images
    ) {

    }

    @Builder
    public record Response(Long id) {
    }

}

package com.moa.MoaS3Example.domain.board.board.repository;

import com.moa.MoaS3Example.domain.board.board.dto.FindBoardDto;
import com.moa.MoaS3Example.domain.board.board.dto.FindBoardDto.Response;
import com.moa.MoaS3Example.domain.board.board.entity.Board;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}

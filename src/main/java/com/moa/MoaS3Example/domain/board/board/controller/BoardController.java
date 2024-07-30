package com.moa.MoaS3Example.domain.board.board.controller;

import com.moa.MoaS3Example.domain.board.board.dto.AddBoardDto;
import com.moa.MoaS3Example.domain.board.board.dto.FindBoardDto;
import com.moa.MoaS3Example.domain.board.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Board-API")
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    @Operation(summary = "게시글 추가", responses = {@ApiResponse(responseCode = "201")})
    public ResponseEntity<AddBoardDto.Response> add(@RequestBody AddBoardDto.Request request) {
        AddBoardDto.Response response = boardService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "게시글 전체 조회", responses = {@ApiResponse(responseCode = "200")})
    public ResponseEntity<List<FindBoardDto.Response>> findAll() {
        List<FindBoardDto.Response> findAll = boardService.findAll();
        return ResponseEntity.ok(findAll);
    }
}

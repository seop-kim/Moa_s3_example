package com.moa.MoaS3Example.domain.board.board.service;

import com.moa.MoaS3Example.domain.board.board.dto.AddBoardDto;
import com.moa.MoaS3Example.domain.board.board.dto.FindBoardDto;
import com.moa.MoaS3Example.domain.board.board.dto.FindBoardDto.Response;
import com.moa.MoaS3Example.domain.board.board.entity.Board;
import com.moa.MoaS3Example.domain.board.board.repository.BoardRepository;
import com.moa.MoaS3Example.global.image.dto.ImageDto;
import com.moa.MoaS3Example.global.image.entity.Image;
import com.moa.MoaS3Example.global.image.service.ImageService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ImageService imageService;

    public AddBoardDto.Response save(AddBoardDto.Request request) {
        Board board = Board.create(request);
        boardRepository.save(board);

        for (String image : request.images()) {
            imageService.save(board, image);
        }

        return AddBoardDto.Response.builder().id(board.getId()).build();
    }

    public List<FindBoardDto.Response> findAll() {
        List<FindBoardDto.Response> responses = new ArrayList<>();
        for (Board board : boardRepository.findAll()) {
            List<Image> findAllImage = imageService.findAll(board);
            responses.add(FindBoardDto.of(board, findAllImage.stream().map(ImageDto::of).collect(Collectors.toList())));
        }
        return responses;
    }

}

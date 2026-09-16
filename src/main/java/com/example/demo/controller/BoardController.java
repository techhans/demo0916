package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.dto.BoardResponseDto;
import com.example.demo.dto.BoardSaveRequestDto;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boards")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 1. 글 저장 (RequestDto 사용 및 ApiResponse 포장)
    @PostMapping
    public ApiResponse<BoardResponseDto> createBoard(@RequestBody BoardSaveRequestDto requestDto) {
        Board savedBoard = boardService.createBoard(requestDto.toEntity());
        return ApiResponse.success(new BoardResponseDto(savedBoard), "게시글이 성공적으로 등록되었습니다.");
    }

    // 2. 글 전체 조회 (ResponseDto 변환 및 ApiResponse 포장)
    @GetMapping
    public ApiResponse<List<BoardResponseDto>> getAllBoards() {
        List<BoardResponseDto> list = boardService.findAllBoards().stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
        return ApiResponse.success(list);
    }

    // 3. 글 상세 조회 (ResponseDto 변환 및 ApiResponse 포장)
    @GetMapping("/{id}")
    public ApiResponse<BoardResponseDto> getBoardById(@PathVariable("id") Long id) {
        Board board = boardService.findBoardById(id);
        return ApiResponse.success(new BoardResponseDto(board));
    }

    // 4. 게시글 수정 (PUT)
    @PutMapping("/{id}")
    public ApiResponse<Void> updateBoard(@PathVariable("id") Long id, @RequestBody BoardSaveRequestDto requestDto) {
        boardService.updateBoard(id, requestDto.getTitle(), requestDto.getContent());
        return ApiResponse.success(null, "게시글이 성공적으로 수정되었습니다.");
    }

    // 5. 게시글 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return ApiResponse.success(null, "게시글이 성공적으로 삭제되었습니다.");
    }



}

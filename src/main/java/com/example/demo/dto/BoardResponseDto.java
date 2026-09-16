package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.Getter;

@Getter
public class BoardResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;

    // Entity를 받아 DTO로 변환하는 생성자
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
    }
}

package com.example.demo.dto;

import com.example.demo.entity.Board; // 본인 엔티티 경로 확인
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class BoardSaveRequestDto {
    private String title;
    private String content;
    private String writer;

    // DTO에 담긴 데이터를 실제 DB 테이블 모양(Entity)으로 변환하는 메서드
    public Board toEntity() {
        Board board = new Board();
        board.setTitle(this.title);
        board.setContent(this.content);
        board.setWriter(this.writer);
        return board;
    }
}

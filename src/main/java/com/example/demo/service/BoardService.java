package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.lang.IllegalArgumentException; // 사실 자동 인포트되지만 확인용


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용 성능 최적화
public class BoardService {

    private final BoardRepository boardRepository;

    // 글 전체 조회 비즈니스 로직
    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    // 글 저장 비즈니스 로직 (여기는 쓰기 작업이므로 @Transactional을 별도로 달거나 readOnly를 끕니다)
    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }


    // BoardService.java 내부에 추가
    public Board findBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
    }

    // BoardService.java 내부에 추가

    // 게시글 수정 (더티 체킹 활용)
    @Transactional
    public void updateBoard(Long id, String title, String content) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        
        // 엔티티의 값을 바꾸면 트랜잭션이 끝날 때 자동으로 DB에 반영됩니다.
        board.update(title, content);
    }

    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
        
        boardRepository.delete(board);
    }

}

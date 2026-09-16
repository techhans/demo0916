package com.example.demo.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 전역 API 예외 처리를 담당하는 애노테이션
public class GlobalExceptionHandler {

    // 우리가 아까 BoardService에서 던진 IllegalArgumentException을 여기서 가로챕니다.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse<Void> response = ApiResponse.error(ex.getMessage(), "BAD_REQUEST_ERROR");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 에러 반환
    }

    // 그 외 예측하지 못한 모든 서버 내부 에러(500) 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllException(Exception ex) {
        ApiResponse<Void> response = ApiResponse.error("서버 내부 오류가 발생했습니다.", "INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // 500 에러 반환
    }
}

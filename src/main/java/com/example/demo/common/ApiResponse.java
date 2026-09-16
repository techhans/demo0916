package com.example.demo.common;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final T data;
    private final String message;
    private final String code;

    // 성공 응답용 생성자
    private ApiResponse(T data, String message) {
        this.success = true;
        this.data = data;
        this.message = message;
        this.code = "SUCCESS";
    }

    // 실패 응답용 생성자
    private ApiResponse(String message, String code) {
        this.success = false;
        this.data = null;
        this.message = message;
        this.code = code;
    }

    // 성공 정적 팩토리 메서드
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, "요청이 성공적으로 처리되었습니다.");
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    // 실패 정적 팩토리 메서드
    public static <T> ApiResponse<T> error(String message, String code) {
        return new ApiResponse<>(message, code);
    }
}

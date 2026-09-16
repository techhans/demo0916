package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/api/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        // 리액트에게 보내줄 데이터 양식 (Key-Value)
        response.put("message", "스프링 부트와 마리아디비 연동 성공!(java Auto Build X)");
        return response; 
    }
}

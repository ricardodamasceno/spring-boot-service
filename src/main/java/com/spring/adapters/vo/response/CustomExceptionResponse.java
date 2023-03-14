package com.spring.adapters.vo.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomExceptionResponse {
    private LocalDateTime time;
    private String message;

    public CustomExceptionResponse(String message){
        this.message = message;
        this.time = LocalDateTime.now();
    }

}

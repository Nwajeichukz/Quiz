package com.example.quizzApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
public class QuizAppResponse <T>{
    private int status;
    private String message;

    private final String timeStamp = String.valueOf(LocalDateTime.now());

    private T data;

    private Collection<Object> error;
    public QuizAppResponse(T data) {
        this.data = data;
    }

    public QuizAppResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public QuizAppResponse(int status, Collection<Object> error) {
        this.status = status;
        this.error = error;
    }

    public QuizAppResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public QuizAppResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

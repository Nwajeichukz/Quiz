package com.example.quizzApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerResponse {
    private int status;
    private int score;

    private Answers ans;


    @Builder
    @Data
    public static class Answers {
        private long option;
        private long questionId;
        private String message;


    }

}

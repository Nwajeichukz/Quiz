package com.example.quizzApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@RequiredArgsConstructor
public class PostQuestionDto {
    @NotBlank(message = "question should not be blank")
    private String question;

    @NotBlank(message = "options cant be blank")
    private List<String> options;

    @NotNull(message = "answer should not be blank")
    private String answer;
}

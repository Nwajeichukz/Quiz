package com.example.quizzApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class QuestionDto {
    @NotBlank(message = "question should not be blank")
    private String question;

    @NotBlank(message = "option A should not be blank")
    private String optionA;

    @NotBlank(message = "option B should not be blank")
    private String optionB;

    @NotBlank(message = "option C should not be blank")
    private String optionC;

    @NotBlank(message = "option D should not be blank")
    private String optionD;

    @NotNull(message = "answer should not be blank")
    private Integer ans;

}

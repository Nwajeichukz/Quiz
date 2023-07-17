package com.example.quizzApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostQuestionDto {
    @NotEmpty(message = "question should not be blank")
    private String question;

    @NotEmpty(message = "options cant be blank")
    private List<String> options;

    @NotNull(message = "point most not be empty")
    private Integer point;

    @NotEmpty(message = "answer should not be blank")
    private String answer;
}

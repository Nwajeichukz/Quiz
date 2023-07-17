package com.example.quizzApp.dto;

import com.example.quizzApp.entity.QuizQuestion;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class QuestionDto {

    private Long id;


    private String question;

    Collection<OptionDto> options;


    public QuestionDto(QuizQuestion question){
        id = question.getId();
        this.question = question.getQuestion();

        options = question.getOptions().stream()
                        .map(o -> new OptionDto(o)
                        ).collect(Collectors.toList());
    }


    public QuestionDto(QuestionDto questionDto) {
    }
}
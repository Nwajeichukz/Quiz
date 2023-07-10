package com.example.quizzApp.dto;

import com.example.quizzApp.entity.QuestionOption;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionDto {

    private Long id;
    private String value;

    private boolean isAnswer;

    private int point;

    public OptionDto(QuestionOption option){
        id = option.getId();
        value = option.getValue();
        isAnswer = option.isAnswer();
        point = option.getPoint();
    }
}

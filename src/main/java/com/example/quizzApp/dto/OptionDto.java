package com.example.quizzApp.dto;

import com.example.quizzApp.entity.QuestionOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

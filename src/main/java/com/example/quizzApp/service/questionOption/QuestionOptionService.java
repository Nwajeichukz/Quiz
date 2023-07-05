package com.example.quizzApp.service.questionOption;

import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.dto.UpdateOptionsDto;

public interface QuestionOptionService {
    QuizAppResponse<?> updateOption(long id, UpdateOptionsDto val);

    QuizAppResponse<?> getAnsAndQuestion(long questionId, long optionId);

}

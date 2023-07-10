package com.example.quizzApp.service.questionOption;

import com.example.quizzApp.dto.*;

import java.util.List;

public interface QuestionOptionService {
    QuizAppResponse<OptionDto> updateOption(long id, UpdateOptionsDto val);

    QuizAppResponse<Integer> getAnsAndQuestion(long questionId, long optionId);

    List<AnswerResponse> getAllAnsAndQuestion(List<AnswersDto> answersDto);
}

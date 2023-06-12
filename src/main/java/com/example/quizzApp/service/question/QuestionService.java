package com.example.quizzApp.service.question;

import com.example.quizzApp.dto.PostQuestionDto;
import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuestionResponse;
import com.example.quizzApp.entity.QuestionForm;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestion();

    QuestionDto getQuestionById(int id);

    List<QuestionForm> getRandomNumber();

    QuestionResponse save(PostQuestionDto postQuestionDto);

    QuestionResponse updatingById(int id, PostQuestionDto postQuestionDto);

    void deleteById(int id);
}

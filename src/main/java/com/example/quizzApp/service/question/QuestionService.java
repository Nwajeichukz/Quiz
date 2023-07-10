package com.example.quizzApp.service.question;

import com.example.quizzApp.dto.PostQuestionDto;
import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuizAppResponse;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface QuestionService {
    QuizAppResponse<Map<String, Object>> getAllQuestion(Pageable pageable);

    QuizAppResponse<QuestionDto> getQuestionById(long id);

    public QuizAppResponse<Map<String, Object>> getRandom(Pageable pageable);
    QuizAppResponse<String> save(PostQuestionDto postQuestionDto);

    QuizAppResponse<String> updatingQuestion(long id, PostQuestionDto postQuestionDto);

    void deleteById(long id);

}

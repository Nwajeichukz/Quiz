package com.example.quizzApp.service;

import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuestionResponse;
import com.example.quizzApp.entity.QuestionForm;
import com.example.quizzApp.enums.ResponseCodeEnum;
import com.example.quizzApp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    public List<QuestionForm> getAllQuestion() {
        return questionRepository.findAll();
    }

    public QuestionResponse save(QuestionDto questionDto) {
        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestion(questionDto.getQuestion());
        questionForm.setOptionA(questionDto.getOptionA());
        questionForm.setOptionB(questionDto.getOptionB());
        questionForm.setOptionC(questionDto.getOptionC());
        questionForm.setOptionD(questionDto.getOptionD());
        questionForm.setAns(questionDto.getAns());



        questionRepository.save(questionForm);
        return new QuestionResponse(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDescription());
    }


}

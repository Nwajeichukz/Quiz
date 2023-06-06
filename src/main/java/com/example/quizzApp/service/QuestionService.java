package com.example.quizzApp.service;

import com.example.quizzApp.Exception.IdNotFoundException;
import com.example.quizzApp.dto.PostQuestionDto;
import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuestionResponse;
import com.example.quizzApp.entity.QuestionForm;
import com.example.quizzApp.enums.ResponseCodeEnum;
import com.example.quizzApp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Service
@AllArgsConstructor
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;


    public List<QuestionDto> getAllQuestion(){
        List<QuestionForm> questionForms = questionRepository.findAll();
        List<QuestionDto> returnedQuestion = new ArrayList<>();

        for (QuestionForm qForm: questionForms){
            returnedQuestion.add(createQuestionDto(qForm));
        }

        return returnedQuestion;
    }

    @Transactional
    public List<QuestionDto> getCategory(String category ){
        List<QuestionForm> qF = questionRepository.findByCategory(category);
        List <QuestionDto> categoryQuestions = new ArrayList<>();
        for (QuestionForm question : qF){
            categoryQuestions.add(createQuestionDto(question));
        }

        return categoryQuestions;
    }


    public QuestionDto getQuestionById(int id) throws IdNotFoundException {
        QuestionForm questionForm = questionRepository.findById(id);

        if (questionForm == null){
            throw new IdNotFoundException("ID Not Found");
        }

        return createQuestionDto(questionForm);
    }




    public QuestionResponse save(PostQuestionDto postQuestionDto) {
        questionRepository.save(createQuestionForm(postQuestionDto));
        return new QuestionResponse(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDescription());
    }


    public List<QuestionForm> getRandomNumber() {
        return questionRepository.findAllById(getRandomNums());
    }


    public Iterable<Integer> getRandomNums() {
        List<QuestionForm> all = questionRepository.findAll();
        long totalNums = all.stream().count();

        Integer randomNumber = (int)(Math.random() * (totalNums)) + 1 ;
        return Collections.singleton(randomNumber);
    }






    private QuestionDto createQuestionDto(QuestionForm questionForm){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(questionForm.getId());
        questionDto.setQuestion(questionForm.getQuestion());
        questionDto.setOptionA(questionForm.getOptionA());
        questionDto.setOptionB(questionForm.getOptionB());
        questionDto.setOptionC(questionForm.getOptionC());
        questionDto.setOptionD(questionForm.getOptionD());
        return questionDto;
    }

    private QuestionForm createQuestionForm(PostQuestionDto postQuestionDto){
        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestion(postQuestionDto.getQuestion());
        questionForm.setOptionA(postQuestionDto.getOptionA());
        questionForm.setOptionB(postQuestionDto.getOptionB());
        questionForm.setOptionC(postQuestionDto.getOptionC());
        questionForm.setOptionD(postQuestionDto.getOptionD());
        questionForm.setQuestionCategory(postQuestionDto.getQuestionCategory());
        questionForm.setAns(postQuestionDto.getAns());

        return questionForm;
    }

}
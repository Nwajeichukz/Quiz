package com.example.quizzApp.service.question;

import com.example.quizzApp.Exception.ApiException;
import com.example.quizzApp.dto.PostQuestionDto;
import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuestionResponse;
import com.example.quizzApp.entity.QuestionForm;
import com.example.quizzApp.enums.ResponseCodeEnum;
import com.example.quizzApp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;


    @Override
    public List<QuestionDto> getAllQuestion(){
        List<QuestionForm> questionForms = questionRepository.findAll();
        List<QuestionDto> returnedQuestion = new ArrayList<>();

        if (questionForms.isEmpty()) throw new ApiException();

        for (QuestionForm qForm: questionForms) returnedQuestion.add(createQuestionDto(qForm));

        return returnedQuestion;
    }

    @Override
    public QuestionDto getQuestionById(int id)  {
        Optional<QuestionForm> questionForm = questionRepository.findById(id);
        QuestionForm returned = questionForm.get();

        if (questionForm == null) throw new ApiException();

        return createQuestionDto(returned);
    }

    @Override
    public List<QuestionForm> getRandomNumber() {
        return questionRepository.findAllById(getRandomNums());
    }

    @Override
    public QuestionResponse save(PostQuestionDto postQuestionDto) {
        questionRepository.save(createQuestionForm(postQuestionDto));
        return new QuestionResponse(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDescription());
    }

    @Override
    public QuestionResponse updatingById(int id, PostQuestionDto postQuestionDto) {
        Optional<QuestionForm> returnedQuestion = questionRepository.findById(id);
        if (returnedQuestion.isPresent()) {
            QuestionForm questionForm = returnedQuestion.get();
            questionForm.setQuestion(postQuestionDto.getQuestion());
            questionForm.setOptionA(postQuestionDto.getOptionA());
            questionForm.setOptionB(postQuestionDto.getOptionB());
            questionForm.setOptionC(postQuestionDto.getOptionC());
            questionForm.setOptionD(postQuestionDto.getOptionD());
            questionForm.setAns(postQuestionDto.getAns());

            questionRepository.save(questionForm);
        }else {
            throw new ApiException();
        }
        return new QuestionResponse(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDescription());
    }


    @Override
    public void deleteById(int id) {
        questionRepository.deleteById(id);
    }

    public Iterable<Integer> getRandomNums() {
        List<QuestionForm> all = questionRepository.findAll();

        if (all.isEmpty()) throw new ApiException();

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
        questionForm.setAns(postQuestionDto.getAns());

        return questionForm;
    }
}
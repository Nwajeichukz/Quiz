package com.example.quizzApp.service;

import com.example.quizzApp.dto.*;
import com.example.quizzApp.entity.QuestionOption;
import com.example.quizzApp.entity.QuizQuestion;
import com.example.quizzApp.repository.OptionRepository;
import com.example.quizzApp.service.questionOption.QuestionOptionServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionOptionServiceTest {

    @Mock
    private OptionRepository optionRepository;

    @InjectMocks
    private QuestionOptionServiceImpl questionOptionService;

    @Test
    public void OptionQuestionService_UpdateOptions_ReturnDto(){
        long optionId = 1l;
        UpdateOptionsDto updateOptionsDto = new UpdateOptionsDto();
        updateOptionsDto.setValue("sabinus");

        QuestionOption questionOption = new QuestionOption();
        questionOption.setValue(updateOptionsDto.getValue());

        when(optionRepository.findById(optionId)).thenReturn(Optional.ofNullable(questionOption));
        when(optionRepository.save(questionOption)).thenReturn(questionOption);

        QuizAppResponse<OptionDto> update = questionOptionService.updateOption(optionId, updateOptionsDto);

        Assertions.assertThat(update).isNotNull();
    }

    @Test
    public void OptionQuestionService_getAnsAndQuestion_Integer(){
        long optionId = 1l;
        long questionId = 1l;

        QuizQuestion quizQuestion = new QuizQuestion();
        QuestionOption option = new QuestionOption();
        option.setId(1l);
        option.setValue("janet");
        option.setPoint(3);
        option.setAnswer(true);
        option.setQuestion(quizQuestion);

        quizQuestion.setId(1l);
        quizQuestion.setQuestion("sabinus");
        quizQuestion.setOptions(List.of(option));

        PostQuestionDto postQuestionDto = PostQuestionDto.builder()
                .question("sabinus")
                .answer("janet")
                .options(List.of("janet", "prof", "super hero"))
                .point(3)
                .build();

        when(optionRepository.findById(optionId)).thenReturn(Optional.ofNullable(option));

        QuizAppResponse<Integer> response = questionOptionService.getAnsAndQuestion(optionId, questionId);

        Assertions.assertThat(response).isNotNull();


    }

    @Test
    public void OptionQuestionService_getAnsAllAndQuestion_Integer(){
        QuizQuestion quizQuestion = new QuizQuestion();
        QuestionOption option1 = new QuestionOption();
        option1.setId(1l);
        option1.setValue("janet");
        option1.setPoint(3);
        option1.setAnswer(true);
        option1.setQuestion(quizQuestion);

        QuestionOption option2 = new QuestionOption();
        option2.setId(2l);
        option2.setValue("sam");
        option2.setPoint(0);
        option2.setAnswer(false);
        option2.setQuestion(quizQuestion);

        quizQuestion.setId(1l);
        quizQuestion.setQuestion("sabinus");
        quizQuestion.setOptions(List.of(option1, option2));


        AnswersDto answersDto = new AnswersDto(1l,2l);

        when(optionRepository.findAllById(List.of(answersDto.getAnswersId()))).thenReturn(List.of(option1, option2));

        Assertions.assertThat(questionOptionService.getAllAnsAndQuestion(List.of(answersDto))).isNotNull();

    }


    @Test
    public void OptionServiceLoadOptions(){
        AnswersDto answersDto = new AnswersDto(1l,1l);
        QuizQuestion quizQuestion = new QuizQuestion();
        QuestionOption option1 = new QuestionOption();
        option1.setId(1l);
        option1.setValue("janet");
        option1.setPoint(3);
        option1.setAnswer(true);
        option1.setQuestion(quizQuestion);

        quizQuestion.setId(1l);
        quizQuestion.setQuestion("sabinus");
        quizQuestion.setOptions(List.of(option1));

        when(optionRepository.findAllById(List.of(answersDto.getAnswersId()))).thenReturn(List.of(option1));
        Map<Long, QuestionOption> returnMap = questionOptionService.loadOptions(List.of(answersDto));

        Assertions.assertThat(returnMap).isNotNull();
    }
}
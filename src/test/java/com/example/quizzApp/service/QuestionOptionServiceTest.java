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
//
//    @Test
//    public void OptionQuestionService_getAnsAllAndQuestion_Integer(){
////        List<Long> optionId = List.of(3l,6l,9l);
////        List<Long> questionId = List.of(1l,2l,3l);
//
//        QuizQuestion quizQuestion = new QuizQuestion();
//        QuestionOption option = new QuestionOption();
//        option.setId(1l);
//        option.setValue("janet");
//        option.setPoint(3);
//        option.setAnswer(true);
//        option.setQuestion(quizQuestion);
//
//        quizQuestion.setId(1l);
//        quizQuestion.setQuestion("sabinus");
//        quizQuestion.setOptions(List.of(option));
//
//        PostQuestionDto postQuestionDto = PostQuestionDto.builder()
//                .question("sabinus")
//                .answer("janet")
//                .options(List.of("janet", "prof", "super hero"))
//                .point(3)
//                .build();
//
//        AnswersDto answersDto = new AnswersDto(1l,3l);
////        AnswersDto answersDto2 = new AnswersDto(2l,6l);
////        AnswersDto answersDto3 = new AnswersDto(3l,9l);
//
//
//
//        when(optionRepository.findAllById(List.of(answersDto.getAnswersId()))).thenReturn(List.of(option));
//
////        questionOptionService.getAllAnsAndQuestion(List.of(answersDto));
//
//        Assertions.assertThat(questionOptionService.getAllAnsAndQuestion(List.of(answersDto))).isNotNull();
//
//
//    }

}

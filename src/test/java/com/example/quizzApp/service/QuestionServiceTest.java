package com.example.quizzApp.service;

import com.example.quizzApp.dto.PostQuestionDto;
import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.entity.QuestionOption;
import com.example.quizzApp.entity.QuizQuestion;
import com.example.quizzApp.repository.QuestionRepository;
import com.example.quizzApp.service.question.QuestionServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Test
    public void QuestionService_CreateQuestion_ReturnsQuestion(){
        QuizQuestion quizQuestion = new QuizQuestion();
        QuestionOption questionOption = new QuestionOption();

        questionOption.setValue("janet");
        questionOption.setAnswer(true);
        questionOption.setPoint(3);
        questionOption.setQuestion(quizQuestion);

        quizQuestion.setQuestion("sabinus");
        quizQuestion.setOptions(List.of(questionOption));

        PostQuestionDto postQuestionDto = PostQuestionDto.builder()
                .question("sabinus")
                .answer("janet")
                .options(List.of("janet", "prof", "super hero"))
                .point(3)
                .build();

        when(questionRepository.save(Mockito.any(QuizQuestion.class))).thenReturn(quizQuestion);

        QuizAppResponse<String> response = questionService.save(postQuestionDto);
        Assertions.assertThat(response).isNotNull();

    }

//    @Test
//    public void QuestionService_GetAllQuestion_ReturnsResponseDto(){
//
//        Page<QuizQuestion> quizQuestions = Mockito.mock(Page.class);
//
//
//        when(questionRepository.findAll(Mockito.any(Pageable.class))).thenReturn(quizQuestions);
//
//        QuizAppResponse<Map<String, Object>> saveQuiz = questionService.getAllQuestion(PageRequest.of(0, 10));
//
//        Assertions.assertThat(saveQuiz).isNotNull();
//    }

    @Test
    public void QuestionService_getById_ReturnResponse(){
        long questionId = 1l;
        QuizQuestion quizQuestion = new QuizQuestion();
        QuestionOption questionOption = new QuestionOption();

        questionOption.setValue("janet");
        questionOption.setAnswer(true);
        questionOption.setPoint(3);
        questionOption.setQuestion(quizQuestion);

        quizQuestion.setQuestion("sabinus");
        quizQuestion.setOptions(List.of(questionOption));

        PostQuestionDto postQuestionDto = PostQuestionDto.builder()
                .question("sabinus")
                .answer("janet")
                .options(List.of("janet", "prof", "super hero"))
                .point(3)
                .build();

        when(questionRepository.findById(questionId)).thenReturn(Optional.ofNullable(quizQuestion));

        QuizAppResponse<QuestionDto> quizReturn = questionService.getQuestionById(questionId);

        Assertions.assertThat(quizReturn).isNotNull();
    }

    @Test
    public void QuestionService_UpdateQuestion_ReturnQuestion(){
        long questionId = 1l;
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuestion("sabinus");

        PostQuestionDto postQuestionDto = PostQuestionDto.builder()
                .question("sabinus")
                .build();

        when(questionRepository.findById(questionId)).thenReturn(Optional.ofNullable(quizQuestion));
        when(questionRepository.save(quizQuestion)).thenReturn(quizQuestion);

        QuizAppResponse<String> updateReturn = questionService.updatingQuestion(questionId,postQuestionDto);
        Assertions.assertThat(updateReturn).isNotNull();
    }

    @Test
    public void QuizQuestionService_DeleteById_ReturnVoid(){
        long questionId = 1l;
        QuizQuestion quizQuestion = new QuizQuestion();
        QuestionOption questionOption = new QuestionOption();

        questionOption.setValue("janet");
        questionOption.setAnswer(true);
        questionOption.setPoint(3);
        questionOption.setQuestion(quizQuestion);

        quizQuestion.setQuestion("sabinus");
        quizQuestion.setOptions(List.of(questionOption));

        PostQuestionDto postQuestionDto = PostQuestionDto.builder()
                .question("sabinus")
                .answer("janet")
                .options(List.of("janet", "prof", "super hero"))
                .point(3)
                .build();

        when(questionRepository.findById(questionId)).thenReturn(Optional.ofNullable(quizQuestion));
        doNothing().when(questionRepository).delete(quizQuestion);

        assertAll(() -> questionService.deleteById(questionId));

    }


}

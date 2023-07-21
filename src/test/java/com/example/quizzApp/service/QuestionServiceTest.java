package com.example.quizzApp.service;

import com.example.quizzApp.dto.OptionDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

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

        QuizAppResponse<String> response = questionService.createQuestion(postQuestionDto);
        Assertions.assertThat(response).isNotNull();

    }




    @Test
    public void QuestionService_GetAllQuestion_ReturnsResponseDto(){
        Pageable pageReq = PageRequest.of(0, 10);
        QuizQuestion quizQuestion = new QuizQuestion();
        QuestionOption questionOption = new QuestionOption();

        questionOption.setValue("janet");
        questionOption.setAnswer(true);
        questionOption.setPoint(3);
        questionOption.setQuestion(quizQuestion);

        quizQuestion.setQuestion("sabinus");
        quizQuestion.setOptions(List.of(questionOption));

        Page<QuizQuestion> quizQuestions = new PageImpl<>(List.of(quizQuestion)  ,pageReq, 1);

        when(questionRepository.findAll(Mockito.any(Pageable.class))).thenReturn(quizQuestions);

        QuizAppResponse<Map<String, Object>> saveQuiz = questionService.getAllQuestion(pageReq);

        Assertions.assertThat(saveQuiz).isNotNull();
    }

    @Test
    public void QuestionService_GetRandomQuiz_ReturnQuiz(){
        Pageable pageReq = PageRequest.of(0, 1);

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

        Page<QuizQuestion> quizQuestions = new PageImpl<>(List.of(quizQuestion), pageReq, 1);

        when(questionRepository.findAll(Mockito.any(Pageable.class))).thenReturn(quizQuestions);

        QuizAppResponse<Map<String, Object>> saveQuiz = questionService.getRandom(pageReq);

        Assertions.assertThat(saveQuiz).isNotNull();
    }


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


    @Test
    public void QuizQuestionService_ShuffleList_void (){
        OptionDto questionOption = new OptionDto();

        questionOption.setValue("janet");
        questionOption.setAnswer(true);
        questionOption.setPoint(3);

        QuestionServiceImpl questionService1 = mock(QuestionServiceImpl.class);

        questionService1.shuffleList(List.of(questionOption));

        Mockito.verify(questionService1,times(1)).shuffleList(List.of(questionOption));
    }


    @Test
    public void QuizQuestionService_DtoToQuestionOptions(){
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

        List<QuestionOption> returnOption = questionService.dtoToQuestionOptions(postQuestionDto, quizQuestion);
        Assertions.assertThat(returnOption).isNotNull();
    }


}

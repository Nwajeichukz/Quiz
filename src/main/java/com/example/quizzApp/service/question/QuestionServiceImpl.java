package com.example.quizzApp.service.question;

import com.example.quizzApp.Exception.ApiException;
import com.example.quizzApp.dto.OptionDto;
import com.example.quizzApp.dto.PostQuestionDto;
import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.entity.QuestionOption;
import com.example.quizzApp.entity.QuizQuestion;
import com.example.quizzApp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;


    @Override
    public QuizAppResponse<Map<String, Object>> getAllQuestion(Pageable pageable) {
        // Converting quizQuestion too QuestionDto loop it and returned it as DTO
        Page<QuestionDto> quizQuestions = questionRepository.findAll(pageable).map(QuestionDto::new);

        // Map.of takes up to 10 keys and values
        Map<String, Object> page = Map.of(
                "page", quizQuestions.getNumber(),
                "totalPages", quizQuestions.getTotalPages(),
                "totalElements", quizQuestions.getTotalElements(),
                "size", quizQuestions.getSize(),
                "content", quizQuestions.getContent()
        );

        return new QuizAppResponse<>("success", page);

    }


    @Override
    public QuizAppResponse<QuestionDto> getQuestionById(long id) {
        QuizQuestion quizQuestion = questionRepository.findById(id).orElseThrow(() -> new ApiException("Question not found"));


        return new QuizAppResponse<>(0,
                "Question successfully retrieved",
                new QuestionDto(quizQuestion)
        );
    }

    @Override
    public QuizAppResponse<Map<String, Object>> getRandom(Pageable pageable) {
        Page<QuestionDto> questions = questionRepository.findAll(pageable)
                .map(question -> new QuestionDto(question))
                .map(dto->{
                    List<OptionDto> modifiableList = new ArrayList<>(dto.getOptions());
                    shuffleList(modifiableList);
                    log.info("--> options before setting to new arraylist {}", dto.getOptions());
                    dto.setOptions(modifiableList);
                    log.info("--> options after setting to shuffled list {}", dto.getOptions());

                    return dto;
                });

        List<QuestionDto> modifiableQuestions = new ArrayList<>(questions.getContent());

        Collections.shuffle(modifiableQuestions, new Random(modifiableQuestions.size()));


        Map<String, Object> page = Map.of(
                "page", questions.getNumber(),
                "totalPages", questions.getTotalPages(),
                "totalElements", questions.getTotalElements(),
                "size", questions.getSize(),
                "content", modifiableQuestions
        );

        return new QuizAppResponse<>(0, "success", page);
    }


    @Override
    public QuizAppResponse<String> createQuestion(PostQuestionDto postQuestionDto) {

        if (CollectionUtils.isEmpty(postQuestionDto.getOptions()))
            throw new ApiException("Question options is required");

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setQuestion(postQuestionDto.getQuestion());

        quizQuestion.setOptions(
                dtoToQuestionOptions(postQuestionDto, quizQuestion)
        );

        questionRepository.save(quizQuestion);

        return new QuizAppResponse<>(0, "Question successfully created", "success");
    }

    @Override
    public QuizAppResponse<String> updatingQuestion(long id, PostQuestionDto postQuestionDto) {
        QuizQuestion quizQuestion = questionRepository.findById(id).orElseThrow(() -> new ApiException("ID do not exist"));

        quizQuestion.setQuestion(postQuestionDto.getQuestion());

        questionRepository.save(quizQuestion);

        return new QuizAppResponse<>(0, "Question successfully updated", quizQuestion.getQuestion());
    }


    @Override
    public void deleteById(long id) {
        QuizQuestion quizQuestion = questionRepository.findById(id).orElseThrow(()-> new ApiException("id do not exist"));
        questionRepository.delete(quizQuestion);
    }


    public List<QuestionOption> dtoToQuestionOptions(PostQuestionDto dto, QuizQuestion question) {
        List<QuestionOption> options = dto.getOptions().stream().map(opt -> {
            QuestionOption option = new QuestionOption();
            option.setQuestion(question);
            option.setValue(opt);
            option.setAnswer(false);
            option.setPoint(0);
            return option;
        }).collect(Collectors.toList());

        //set the answer
        final int answerIndex = dto.getOptions().indexOf(dto.getAnswer());
        if (answerIndex < 0)
            throw new ApiException("No answer provided");

        options.get(answerIndex).setAnswer(true);
        options.get(answerIndex).setPoint(dto.getPoint());

        return options;
    }

    public void shuffleList(List<OptionDto> a){
        int n = a.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }

    public void swap(List<OptionDto> a, int i, int change){
        OptionDto helper = a.get(i);
        a.set(i,a.get(change));
        a.set(change,helper);
    }
}
package com.example.quizzApp.service.questionOption;


import com.example.quizzApp.Exception.ApiException;
import com.example.quizzApp.dto.*;
import com.example.quizzApp.entity.QuestionOption;
import com.example.quizzApp.entity.QuizQuestion;
import com.example.quizzApp.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private final OptionRepository optionRepository;

    int score = 0;

    @Override
    public QuizAppResponse<OptionDto> updateOption(long id, UpdateOptionsDto val) {
        QuestionOption questionOption = optionRepository.findById(id).orElseThrow(() -> new ApiException("Option do not exist"));
        questionOption.setValue(val.getValue());

        optionRepository.save(questionOption);

        return new QuizAppResponse<>(0, "Question successfully updated",
                new OptionDto(questionOption.getId(), questionOption.getValue(), questionOption.isAnswer(), questionOption.getPoint()));
    }

    @Override
    public QuizAppResponse<Integer> getAnsAndQuestion(long questionId, long optionId){

        QuestionOption option = optionRepository.findById(optionId).orElseThrow(() -> new ApiException("do not exist"));

        QuizQuestion optionEntityQuestion = option.getQuestion();
        long optionQuestion = optionEntityQuestion.getId();

        if (optionQuestion == questionId ) {
            int point = option.getPoint();
            score += point;
        }else{
            return new QuizAppResponse<>(-1, "do not match");
        }


        return new QuizAppResponse<>(0, "success", score);
    }

    @Override
    public List<AnswerResponse> getAllAnsAndQuestion(List<AnswersDto> answers){
        List<AnswerResponse> answerResponses = new ArrayList<>(0);

         Map<Long, QuestionOption> optionMap = loadOptions(answers);

        for (AnswersDto answer: answers) {
            Long optionId = answer.getAnswersId();
            QuestionOption option = optionMap.get(optionId);

            Long questionId = answer.getQuestionsId();

            long optionQuestionId = option.getQuestion().getId();

            if (optionQuestionId == questionId ) {
                int point = option.getPoint();
                score += point;
            }else{
                  throw new ApiException("question and option miss match");
            }
            answerResponses.add(AnswerResponse.builder()
                    .ans(
                            AnswerResponse.Answers.builder()
                                    .option(optionId)
                                    .questionId(questionId)
                                    .message("success")
                                    .build()
                    )
                    .score(score)
                    .status(0)
                    .build());
        }

        return answerResponses;

    }

    public Map<Long, QuestionOption> loadOptions(List<AnswersDto> answers) {
        List<Long> optionIds = answers.stream().map(answer -> answer.getAnswersId()).collect(Collectors.toList());

        List<QuestionOption> options = optionRepository.findAllById(optionIds);

        Map<Long, QuestionOption> optionMap = new HashMap<>();

        for (QuestionOption option: options) {
            optionMap.put(option.getId(), option);
        }

        return optionMap;
    }

}
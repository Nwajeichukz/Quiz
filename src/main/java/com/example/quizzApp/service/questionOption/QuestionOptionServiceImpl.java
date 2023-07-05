package com.example.quizzApp.service.questionOption;


import com.example.quizzApp.Exception.ApiException;
import com.example.quizzApp.dto.OptionDto;
import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.dto.UpdateOptionsDto;
import com.example.quizzApp.entity.QuestionOption;
import com.example.quizzApp.entity.QuizQuestion;
import com.example.quizzApp.repository.OptionRepository;
import com.example.quizzApp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    int ans = 0;
    String res = null;

    @Override
    public QuizAppResponse<OptionDto> updateOption(long id, UpdateOptionsDto val) {
        QuestionOption questionOption = optionRepository.findById(id).orElseThrow(() -> new ApiException("Option do not exist"));
        questionOption.setValue(val.getValue());

        optionRepository.save(questionOption);

        return new QuizAppResponse<>(0, "Question successfully updated",
                new OptionDto(questionOption.getId(), questionOption.getValue(), questionOption.isAnswer()));
    }

    @Override
    public QuizAppResponse<?> getAnsAndQuestion(long questionId, long optionId){
        QuestionOption option = optionRepository.findById(optionId).get();

        QuizQuestion optionEntityQuestion = option.getQuestion();
        long optionQuestion = optionEntityQuestion.getId();

        if (optionQuestion == questionId ) {
            res = "correct";
            if (option.isAnswer()){
                ans++;
            }else{
                ans += 0;
            }

        }else{
            res = "wrong";
        }


        return
                new QuizAppResponse<>(0, res, ans);
    }
}
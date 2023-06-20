package com.example.quizzApp.service.questionOption;


import com.example.quizzApp.Exception.ApiException;
import com.example.quizzApp.dto.OptionDto;
import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.dto.UpdateOptionsDto;
import com.example.quizzApp.entity.QuestionOption;
import com.example.quizzApp.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {
    private final OptionRepository optionRepository;

    @Override
    public QuizAppResponse<OptionDto> updateOption(long id, UpdateOptionsDto val) {
        QuestionOption questionOption = optionRepository.findById(id).orElseThrow(() -> new ApiException("Option do not exist"));
        questionOption.setValue(val.getValue());

        optionRepository.save(questionOption);

        return new QuizAppResponse<>(0, "Question successfully updated",
                new OptionDto(questionOption.getId(), questionOption.getValue(), questionOption.isAnswer()));
    }
}

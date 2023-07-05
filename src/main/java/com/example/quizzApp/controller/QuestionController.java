package com.example.quizzApp.controller;

import com.example.quizzApp.dto.*;
import com.example.quizzApp.service.question.QuestionService;
import com.example.quizzApp.service.questionOption.QuestionOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionServiceImpl;
    private final QuestionOptionService questionOptionService;

    @GetMapping
    public ResponseEntity<QuizAppResponse<Map<String, Object>>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ){
        return ResponseEntity.ok(questionServiceImpl.getAllQuestion(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizAppResponse<QuestionDto>> getQuestionById(@PathVariable long id){
        return  ResponseEntity.ok(
                questionServiceImpl.getQuestionById(id)
        );
    }

    @GetMapping("/random")
    public ResponseEntity<QuizAppResponse<Map<String, Object>>> getRandom(Pageable page){
        return ResponseEntity.ok(questionServiceImpl.getRandom(page));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<QuizAppResponse<?>> updatingQuestion(@PathVariable long id, @RequestBody PostQuestionDto postQuestionDto){
        return
                ResponseEntity.ok(
                        questionServiceImpl.updatingQuestion(id, postQuestionDto)
                );
    }


    @GetMapping("/ans/{questionId}/{optionId}")
    public ResponseEntity<QuizAppResponse<?>> getAnsAndQuestion(@PathVariable("questionId")long questionId , @PathVariable("optionId") long optionId){
        return ResponseEntity.ok(questionOptionService.getAnsAndQuestion(questionId, optionId));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("opt/{id}")
    public ResponseEntity<QuizAppResponse<?>> updatingOptions(@PathVariable long id, @RequestBody UpdateOptionsDto val){
        return
                ResponseEntity.ok(
                        questionOptionService.updateOption(id, val)
                );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable long id){
        questionServiceImpl.deleteById(id);
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuizAppResponse<?>> create(@RequestBody PostQuestionDto postQuestionDto){
        return ResponseEntity.ok(
                questionServiceImpl.save(postQuestionDto)
                );
    }

}
package com.example.quizzApp.controller;

import com.example.quizzApp.Exception.IdNotFoundException;
import com.example.quizzApp.dto.PostQuestionDto;
import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuestionResponse;
import com.example.quizzApp.entity.QuestionForm;
import com.example.quizzApp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private final QuestionService questionService;


    @GetMapping("all-questions")
    public List<QuestionDto> getAll(){
        return questionService.getAllQuestion();
    }


    @GetMapping("question-by-id/{id}")
    public QuestionDto getQuestionById(@PathVariable int id) throws IdNotFoundException {
        return questionService.getQuestionById(id);
    }
    @GetMapping("get-category/{category}")
    public List<QuestionDto> getByCategory(@PathVariable String category ) {
        return questionService.getCategory(category);
    }

    @GetMapping("random-question")
    public List<QuestionForm> getRandom(){
        return questionService.getRandomNumber();
    }



    @PostMapping("create-questions")
    public ResponseEntity<QuestionResponse> create(@Valid @RequestBody PostQuestionDto postQuestionDto){
        return ResponseEntity.ok(questionService.save(postQuestionDto));
    }



}

package com.example.quizzApp.controller;

import com.example.quizzApp.dto.PostQuestionDto;
import com.example.quizzApp.dto.QuestionDto;
import com.example.quizzApp.dto.QuestionResponse;
import com.example.quizzApp.entity.QuestionForm;
import com.example.quizzApp.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionServiceImpl;

    @GetMapping
    public List<QuestionDto> getAll(){
        return questionServiceImpl.getAllQuestion();
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable int id){
        return new ResponseEntity<>(questionServiceImpl.getQuestionById(id), HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<List<QuestionForm>> getRandom(){
        return new ResponseEntity<>(questionServiceImpl.getRandomNumber(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponse> updatingQuestion(@PathVariable int id, @RequestBody PostQuestionDto postQuestionDto){
        return new ResponseEntity<>(questionServiceImpl.updatingById(id, postQuestionDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable int id){
        questionServiceImpl.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> create(@Valid @RequestBody PostQuestionDto postQuestionDto){
        return new ResponseEntity<>(
                questionServiceImpl.save(postQuestionDto),
                HttpStatus.CREATED);
    }
}
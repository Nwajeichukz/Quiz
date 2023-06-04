package com.example.quizzApp.controller;

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

    @GetMapping("/all")
    public String get(){
        return "these are all";
    }

    @GetMapping("all-questions")
    public List<QuestionForm> getAll(){
        return questionService.getAllQuestion();
    }

    @PostMapping("create-questions")
    public ResponseEntity<QuestionResponse> create(@Valid @RequestBody QuestionDto questionDto){
        return ResponseEntity.ok(questionService.save(questionDto));
    }


}

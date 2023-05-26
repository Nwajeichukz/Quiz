package com.example.quizzApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quiz")
public class UserController {
    @GetMapping("/hello")
    public String get(){
        return "HELLO QUIZ";
    }
}

package com.example.quizzApp.controller;

import com.example.quizzApp.dto.AuthenticationRequest;
import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.dto.RegisterRequest;
import com.example.quizzApp.service.authentication.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(){
        return "welcome";
    };

    @PostMapping("/signup")
    public ResponseEntity<QuizAppResponse<Map<String, Object>>> createUser(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PostMapping("/admin")
    public ResponseEntity<QuizAppResponse<Map<String, Object>>> createAdmin(@Valid  @RequestBody RegisterRequest request){
        return ResponseEntity.ok(userService.createAdmin(request));
    }

    @PostMapping("/login")
    public ResponseEntity<QuizAppResponse<String>> login(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(userService.login(authenticationRequest));
    }
}

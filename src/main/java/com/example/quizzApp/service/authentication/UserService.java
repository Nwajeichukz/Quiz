package com.example.quizzApp.service.authentication;

import com.example.quizzApp.dto.AuthenticationRequest;
import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.dto.RegisterRequest;

import java.util.Map;

public interface UserService {
    QuizAppResponse<Map<String, Object>> createUser(RegisterRequest request);

    QuizAppResponse<Map<String, Object>> createAdmin(RegisterRequest request);

    QuizAppResponse<String> login(AuthenticationRequest authenticationRequest);

    QuizAppResponse<?> getAllU();
}

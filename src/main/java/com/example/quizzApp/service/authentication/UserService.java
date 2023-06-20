package com.example.quizzApp.service.authentication;

import com.example.quizzApp.dto.AuthenticationRequest;
import com.example.quizzApp.dto.QuizAppResponse;
import com.example.quizzApp.dto.RegisterRequest;

public interface UserService {
    QuizAppResponse<?> createUser(RegisterRequest request);

    QuizAppResponse<?> createAdmin(RegisterRequest request);

    QuizAppResponse<?> login(AuthenticationRequest authenticationRequest);
}

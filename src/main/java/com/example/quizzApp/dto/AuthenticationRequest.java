package com.example.quizzApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Email(message = "wrong email format")
    private String email;

    private String password;

}

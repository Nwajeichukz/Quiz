package com.example.quizzApp.Exception;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public ApiException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ApiException(String message) {
        super(message);
        this.errorMessage = message;
    }
}
package com.example.quizzApp.enums;

public enum ResponseCodeEnum {
    SUCCESS(0, "Success"),
    INVALID_INPUT(-1, "ERROR");

    private int code;
    private String description;

    ResponseCodeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
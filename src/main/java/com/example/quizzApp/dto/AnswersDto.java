package com.example.quizzApp.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswersDto {

   @NotNull(message = "questionsId most not be empty")
   Long questionsId;

   @NotNull(message = "answersId most not be empty")
   Long answersId;
}

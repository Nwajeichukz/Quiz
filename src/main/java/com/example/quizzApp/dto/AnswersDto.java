package com.example.quizzApp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswersDto {

   @NotNull(message = "questionsId most not be empty")
   Long questionsId;

   @NotNull(message = "answersId most not be empty")
   Long answersId;
}

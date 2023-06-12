package com.example.quizzApp.configuration;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("quizapp")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {
  AppInfo apiInfo;

}

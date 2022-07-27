package com.example.simplewebquiz.controller.dto;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestQuizDto {
    String title;
    String text;
    List<String> options;
    int answer;
}

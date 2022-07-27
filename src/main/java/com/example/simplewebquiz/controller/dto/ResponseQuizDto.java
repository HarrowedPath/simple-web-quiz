package com.example.simplewebquiz.controller.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseQuizDto {
    long id;
    String title;
    String text;
    List<String> options;
}
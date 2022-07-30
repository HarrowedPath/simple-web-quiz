package com.example.simplewebquiz.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestAnswerDto {
    List<Integer> answer;
}

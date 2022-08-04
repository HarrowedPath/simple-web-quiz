package com.example.simplewebquiz.controller.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseQuizDto {

    Long id;

    String title;

    String text;

    List<String> options;
}
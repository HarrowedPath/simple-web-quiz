package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.domain.Quiz;
import com.example.simplewebquiz.controller.dto.QuizDto;
import org.springframework.stereotype.Component;

@Component
public class QuizMapper {
    public QuizDto toDto(Quiz quiz) {
        return QuizDto.builder()
                .title(quiz.getTitle())
                .text(quiz.getText())
                .options(quiz.getOptions())
                .build();
    }
}

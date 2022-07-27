package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.domain.Quiz;
import com.example.simplewebquiz.controller.dto.ResponseQuizDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseQuizMapper {
    public ResponseQuizDto toDto(Quiz quiz) {
        return ResponseQuizDto.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .text(quiz.getText())
                .options(quiz.getOptions())
                .build();
    }

    public List<ResponseQuizDto> allToDto(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}

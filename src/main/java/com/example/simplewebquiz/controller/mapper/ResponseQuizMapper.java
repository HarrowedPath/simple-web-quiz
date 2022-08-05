package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.controller.dto.ResponseQuizDto;
import com.example.simplewebquiz.domain.Quiz;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

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

    public Page<ResponseQuizDto> allToDto(Page<Quiz> quizzes) {
        val collect = quizzes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}

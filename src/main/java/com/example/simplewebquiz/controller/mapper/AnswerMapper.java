package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.controller.dto.AnswerQuizDto;
import com.example.simplewebquiz.domain.Quiz;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AnswerMapper {
    public Optional<AnswerQuizDto> toDto(int answer, Quiz quiz) {
        if (answer == quiz.getAnswer()) {
            return Optional.of(AnswerQuizDto.builder()
                    .status(true)
                    .feedback("Congratulations, you're right!")
                    .build());
        }
        return Optional.of(AnswerQuizDto.builder()
                .status(false)
                .feedback("Wrong answer! Please, try again.")
                .build());
    }
}

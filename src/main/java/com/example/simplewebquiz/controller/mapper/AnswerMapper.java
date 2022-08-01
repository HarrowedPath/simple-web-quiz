package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.controller.dto.RequestAnswerDto;
import com.example.simplewebquiz.controller.dto.ResponseAnswerDto;
import com.example.simplewebquiz.domain.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class AnswerMapper {
    public Optional<ResponseAnswerDto> toDto(RequestAnswerDto answer, Quiz quiz) {
        if(quiz.getAnswer()== null) {
            quiz.setAnswer(new ArrayList<>());
        }
        if (quiz.getAnswer().equals(answer.getAnswer())) {
            return Optional.of(ResponseAnswerDto.builder()
                    .status(true)
                    .feedback("Congratulations, you're right!")
                    .build());
        }
        return Optional.of(ResponseAnswerDto.builder()
                .status(false)
                .feedback("Wrong answer! Please, try again.")
                .build());
    }
}

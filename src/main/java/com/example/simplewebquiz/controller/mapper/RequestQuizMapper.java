package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.domain.Quiz;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class RequestQuizMapper {
    public Quiz toModel(com.example.simplewebquiz.controller.dto.RequestQuizDto newQuizDto) {
        val quiz = new Quiz();
        quiz.setTitle(newQuizDto.getTitle());
        quiz.setText(newQuizDto.getText());
        quiz.setOptions(newQuizDto.getOptions());
        quiz.setAnswer(newQuizDto.getAnswer());
        return quiz;
    }
}

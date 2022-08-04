package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.controller.dto.RequestQuizDto;
import com.example.simplewebquiz.domain.Quiz;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class RequestQuizMapper {
    public Quiz toModel(RequestQuizDto requestQuizDto) {
        val quiz = new Quiz();
        quiz.setTitle(requestQuizDto.getTitle());
        quiz.setText(requestQuizDto.getText());
        quiz.setOptions(requestQuizDto.getOptions());
        quiz.setAnswer(requestQuizDto.getAnswer());
        return quiz;
    }
}

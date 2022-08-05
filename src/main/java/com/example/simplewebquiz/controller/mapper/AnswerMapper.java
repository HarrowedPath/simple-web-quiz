package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.controller.dto.RequestAnswerDto;
import com.example.simplewebquiz.controller.dto.ResponseAnswerDto;
import com.example.simplewebquiz.domain.Quiz;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AnswerMapper {
    public Optional<ResponseAnswerDto> toDto(RequestAnswerDto answer, Quiz quiz) {
        if (quiz.getAnswer() == null) {
            quiz.setAnswer(new ArrayList<>());
        }
        if (unorderedEquals(quiz.getAnswer(),answer.getAnswer())) {
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

    private static boolean unorderedEquals(Collection<?> coll1, Collection<?> coll2) {
        if(coll1.size() != coll2.size()) return false;
        Map<Object, Integer> freq = new HashMap<>();
        for(Object o: coll1) freq.merge(o, 1, Integer::sum);
        for(Object o: coll2)
            if(freq.merge(o, -1, Integer::sum) < 0) return false;
        return true;
    }
}

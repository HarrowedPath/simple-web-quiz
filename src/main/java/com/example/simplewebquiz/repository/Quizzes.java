package com.example.simplewebquiz.repository;

import com.example.simplewebquiz.domain.Quiz;
import com.example.simplewebquiz.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Quizzes {
    private final List<Quiz> quizList = new ArrayList<>();

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void add(Quiz quiz) {
        quiz.setId(quizList.size() + 1);
        quizList.add(quiz);
    }

    public Quiz getById(long id) {
        return quizList.stream()
                .filter(q -> q.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("not found"));
    }
}

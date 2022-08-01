package com.example.simplewebquiz.service;

import com.example.simplewebquiz.domain.Quiz;
import com.example.simplewebquiz.repository.QuizRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class QuizService {
    QuizRepository quizRepository;

    public Quiz findById(Long id) {
        return quizRepository.findById(id).orElseThrow();
    }

    public void save (Quiz toSave) {
        quizRepository.save(toSave);
    }

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }
}

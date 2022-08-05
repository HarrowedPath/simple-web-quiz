package com.example.simplewebquiz.service;

import com.example.simplewebquiz.domain.Quiz;
import com.example.simplewebquiz.exception.QuizNotFoundException;
import com.example.simplewebquiz.repository.QuizRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class QuizService {
    QuizRepository quizRepository;

    public Quiz findById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow();
    }

    public void delete(Long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return;
        }
        throw new QuizNotFoundException();
    }

    public void save(Quiz toSave) {
        quizRepository.save(toSave);
    }

    public Page<Quiz> findAll(int pageNumber, int pageSize, String sortBy) {
        val paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return quizRepository.findAll(paging);
    }
}

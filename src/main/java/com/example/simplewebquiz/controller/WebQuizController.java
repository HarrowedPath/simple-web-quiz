package com.example.simplewebquiz.controller;

import com.example.simplewebquiz.controller.dto.RequestQuizDto;
import com.example.simplewebquiz.controller.dto.ResponseQuizDto;
import com.example.simplewebquiz.controller.dto.AnswerQuizDto;
import com.example.simplewebquiz.controller.mapper.RequestQuizMapper;
import com.example.simplewebquiz.controller.mapper.ResponseQuizMapper;
import com.example.simplewebquiz.controller.mapper.AnswerMapper;
import com.example.simplewebquiz.domain.Quiz;
import com.example.simplewebquiz.exception.NotFoundException;
import com.example.simplewebquiz.repository.Quizzes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@AllArgsConstructor
@RequestMapping("api/quizzes")
public class WebQuizController {

    ResponseQuizMapper responseQuizMapper;
    AnswerMapper answerMapper;
    RequestQuizMapper requestQuizMapper;
    Quizzes quizzes;

    @PostMapping
    public ResponseQuizDto addQuiz(@RequestBody RequestQuizDto newQuizDto) {
        val quiz = requestQuizMapper.toModel(newQuizDto);
        quizzes.add(quiz);
        return responseQuizMapper.toDto(quiz);
    }

    @GetMapping
    public List<ResponseQuizDto> getAllQuizzes() {
        return responseQuizMapper.allToDto(quizzes.getQuizList());
    }

    @GetMapping("/{id}")
    public ResponseQuizDto getQuiz(@PathVariable long id) {
        return responseQuizMapper.toDto(quizzes.getById(id));
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<AnswerQuizDto> solveQuiz(@RequestParam int answer, @PathVariable long id) {
        val quiz = quizzes.getById(id);
        val result = answerMapper.toDto(answer, quiz).orElseThrow(() -> new NotFoundException("not found"));
        return ResponseEntity.ok(result);
    }
}

package com.example.simplewebquiz.controller;

import com.example.simplewebquiz.controller.dto.RequestAnswerDto;
import com.example.simplewebquiz.controller.dto.ResponseAnswerDto;
import com.example.simplewebquiz.controller.dto.RequestQuizDto;
import com.example.simplewebquiz.controller.dto.ResponseQuizDto;
import com.example.simplewebquiz.controller.mapper.AnswerMapper;
import com.example.simplewebquiz.controller.mapper.RequestQuizMapper;
import com.example.simplewebquiz.controller.mapper.ResponseQuizMapper;
import com.example.simplewebquiz.exception.NotFoundException;
import com.example.simplewebquiz.repository.Quizzes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@AllArgsConstructor
@RequestMapping("api/quizzes")
@Validated
public class WebQuizController {

    ResponseQuizMapper responseQuizMapper;
    AnswerMapper answerMapper;
    RequestQuizMapper requestQuizMapper;
    Quizzes quizzes;

    @PostMapping
    public ResponseQuizDto addQuiz(@RequestBody @Valid RequestQuizDto newQuizDto) {
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
    public ResponseEntity<ResponseAnswerDto> solveQuiz(@RequestBody RequestAnswerDto answer, @PathVariable long id) {
        val quiz = quizzes.getById(id);
        val result = answerMapper.toDto(answer, quiz).orElseThrow(() -> new NotFoundException("not found"));
        return ResponseEntity.ok(result);
    }
}

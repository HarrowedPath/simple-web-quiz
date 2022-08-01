package com.example.simplewebquiz.controller;

import com.example.simplewebquiz.controller.dto.RequestAnswerDto;
import com.example.simplewebquiz.controller.dto.ResponseAnswerDto;
import com.example.simplewebquiz.controller.dto.RequestQuizDto;
import com.example.simplewebquiz.controller.dto.ResponseQuizDto;
import com.example.simplewebquiz.controller.mapper.AnswerMapper;
import com.example.simplewebquiz.controller.mapper.RequestQuizMapper;
import com.example.simplewebquiz.controller.mapper.ResponseQuizMapper;
import com.example.simplewebquiz.exception.NotFoundException;
import com.example.simplewebquiz.service.QuizService;
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
    QuizService quizService;

    @PostMapping
    public ResponseQuizDto addQuiz(@RequestBody @Valid RequestQuizDto newQuizDto) {
        val quiz = requestQuizMapper.toModel(newQuizDto);
        quizService.save(quiz);
        return responseQuizMapper.toDto(quiz);
    }

    @GetMapping
    public List<ResponseQuizDto> getAllQuizzes() {
        return responseQuizMapper.allToDto(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseQuizDto getQuiz(@PathVariable Long id) {
        return responseQuizMapper.toDto(quizService.findById(id));
    }

    @PostMapping("/{id}/solve")
    public ResponseEntity<ResponseAnswerDto> solveQuiz(@RequestBody RequestAnswerDto answer, @PathVariable Long id) {
        val quiz = quizService.findById(id);
        val result = answerMapper.toDto(answer, quiz).orElseThrow(() -> new NotFoundException("not found"));
        return ResponseEntity.ok(result);
    }
}

package com.example.simplewebquiz.controller;

import com.example.simplewebquiz.controller.dto.*;
import com.example.simplewebquiz.controller.mapper.AnswerMapper;
import com.example.simplewebquiz.controller.mapper.RequestQuizMapper;
import com.example.simplewebquiz.controller.mapper.ResponseQuizMapper;
import com.example.simplewebquiz.controller.mapper.RequestUserMapper;
import com.example.simplewebquiz.exception.QuizNotFoundException;
import com.example.simplewebquiz.security.UserDetailsImpl;
import com.example.simplewebquiz.service.QuizService;
import com.example.simplewebquiz.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class QuizController {

    ResponseQuizMapper responseQuizMapper;
    AnswerMapper answerMapper;
    RequestQuizMapper requestQuizMapper;
    RequestUserMapper requestUserMapper;
    QuizService quizService;
    UserService userService;

    @PostMapping("/quizzes")
    public ResponseQuizDto addQuiz(@RequestBody @Valid RequestQuizDto newQuizDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        val quiz = requestQuizMapper.toModel(newQuizDto);
        quiz.setUser(userDetails.user());
        quizService.save(quiz);
        return responseQuizMapper.toDto(quiz);
    }

    @GetMapping("/quizzes")
    public List<ResponseQuizDto> getAllQuizzes() {
        return responseQuizMapper.allToDto(quizService.findAll());
    }

    @GetMapping("/quizzes/{id}")
    public ResponseQuizDto getQuiz(@PathVariable Long id) {
        return responseQuizMapper.toDto(quizService.findById(id));
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<ResponseAnswerDto> solveQuiz(@RequestBody RequestAnswerDto answer, @PathVariable Long id) {
        val quiz = quizService.findById(id);
        val result = answerMapper.toDto(answer, quiz).orElseThrow(QuizNotFoundException::new);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (quizService.findById(id).getUser().getEmail()
                .equals(userDetails.user().getEmail())) {
            quizService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody RequestUserDto userDto) {
        userService.registerNewUser(requestUserMapper.toModel(userDto));
        return ResponseEntity.ok().build();
    }
}

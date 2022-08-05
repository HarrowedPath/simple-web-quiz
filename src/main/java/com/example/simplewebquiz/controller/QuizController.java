package com.example.simplewebquiz.controller;

import com.example.simplewebquiz.controller.dto.*;
import com.example.simplewebquiz.controller.mapper.*;
import com.example.simplewebquiz.exception.QuizNotFoundException;
import com.example.simplewebquiz.security.UserDetailsImpl;
import com.example.simplewebquiz.service.QuizService;
import com.example.simplewebquiz.service.SolveService;
import com.example.simplewebquiz.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class QuizController {
    ResponseQuizMapper responseQuizMapper;
    AnswerMapper answerMapper;
    RequestQuizMapper requestQuizMapper;
    UserMapper requestUserMapper;
    QuizService quizService;
    UserService userService;
    SolveService solveService;
    SolveMapper solveMapper;

    @PostMapping("/quizzes")
    public ResponseQuizDto addQuiz(@RequestBody @Valid RequestQuizDto newQuizDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        val quiz = requestQuizMapper.toModel(newQuizDto);
        quiz.setUser(userDetails.user());
        quizService.save(quiz);
        return responseQuizMapper.toDto(quiz);
    }

    @GetMapping("/quizzes")
    public Page<ResponseQuizDto> getAllQuizzes(@Min(0) @RequestParam(defaultValue = "0") int page,
                                               @Min(10) @RequestParam(defaultValue = "10") int pageSize,
                                               @RequestParam(defaultValue = "id") String sortB) {
        val all = quizService.findAll(page, pageSize, sortB);
        return responseQuizMapper.allToDto(all);
    }

    @GetMapping("/quizzes/{id}")
    public ResponseQuizDto getQuiz(@PathVariable Long id) {
        return responseQuizMapper.toDto(quizService.findById(id));
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<ResponseAnswerDto> solveQuiz(@RequestBody RequestAnswerDto answer,
                                                       @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        val quiz = quizService.findById(id);
        val result = answerMapper.toDto(answer, quiz).orElseThrow(QuizNotFoundException::new);
        if (result.getStatus()) {
            val solve = solveMapper.quizToSolve(quiz);
            solve.setUser(userDetails.user());
            solve.setUserEmail(userDetails.user().getEmail());
            solve.setQuiz(quiz);
            solveService.save(solve);
        }
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
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody UserDto userDto) {
        val user = requestUserMapper.toModel(userDto);
        userService.registerNewUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/quizzes/completed")
    public Page<SolveDto> getUserCompletions(
            @Min(0) @RequestParam(defaultValue = "0") int page,
            @Min(10) @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "completedAt") String sortBy,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        val solvePage = solveService.findAll(userDetails.user().getEmail(), page, pageSize, sortBy);
        return solveMapper.allToDto(solvePage);
    }
}

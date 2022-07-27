package com.example.simplewebquiz.controller;

import com.example.simplewebquiz.domain.Quiz;
import com.example.simplewebquiz.controller.dto.QuizDto;
import com.example.simplewebquiz.controller.dto.ResultDto;
import com.example.simplewebquiz.controller.mapper.QuizMapper;
import com.example.simplewebquiz.controller.mapper.ResultMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@AllArgsConstructor
@RequestMapping("api")
public class WebQuizController {

    QuizMapper quizMapper;
    ResultMapper resultMapper;


    @PostMapping("/quiz")
    public ResponseEntity<ResultDto> sendAnswer(@RequestParam int answer) {
        return ResponseEntity.ok(resultMapper.toDto(answer));
    }

    @GetMapping("/quiz")
    public QuizDto getQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTitle("The Java Logo");
        quiz.setText("What is depicted on the Java logo?");
        quiz.setOptions(List.of("Robot", "Tea leaf", "Cup of coffee", "Bug"));
        return quizMapper.toDto(quiz);
    }
}

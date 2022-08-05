package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.controller.dto.SolveDto;
import com.example.simplewebquiz.domain.Quiz;
import com.example.simplewebquiz.domain.Solve;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SolveMapper {
    public Solve quizToSolve(Quiz quiz) {
        val Solve = new Solve();
        Solve.setId(quiz.getId());
        return Solve;
    }

    public SolveDto toDto(Solve solve) {
        return SolveDto.builder()
                .quizId(solve.getId())
                .completedAt(solve.getCompletedAt())
                .build();
    }
    public Page<SolveDto> allToDto(Page<Solve> solvePage) {
        val collect = solvePage.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return new PageImpl<>(collect);
    }
}

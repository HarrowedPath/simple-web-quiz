package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.controller.dto.ResultDto;
import org.springframework.stereotype.Component;

@Component
public class ResultMapper {
    public ResultDto toDto(int answer) {
        if (answer == 2) {
            return ResultDto.builder()
                    .status(true)
                    .feedback("Congratulations, you're right!")
                    .build();
        }
        return ResultDto.builder()
                .status(false)
                .feedback("Wrong answer! Please, try again.")
                .build();
    }
}

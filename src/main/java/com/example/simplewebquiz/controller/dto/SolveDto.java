package com.example.simplewebquiz.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SolveDto {
    @JsonProperty(value = "id")
    Long quizId;

    LocalDateTime completedAt;
}

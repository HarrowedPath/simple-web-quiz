package com.example.simplewebquiz.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SolveId implements Serializable {
    @Serial
    private static final long serialVersionUID = 8730599536453858883L;

    String userEmail;

    Long id;

    LocalDateTime completedAt;
}
package com.example.simplewebquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@IdClass(SolveId.class)
public class Solve {
    @Id
    @Column(name = "USER_ID")
    String userEmail;

    @JsonProperty("id")
    @Id
    @Column(name = "QUIZ_ID")
    Long id;

    @JsonProperty("completedAt")
    @Id
    LocalDateTime completedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "USER_ID",
            insertable = false,
            updatable = false)
    User user;

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID",
            insertable = false,
            updatable = false)
    Quiz quiz;
}
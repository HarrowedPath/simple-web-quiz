package com.example.simplewebquiz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "User_Table")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class User {
    @Id
    String email;

    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Quiz> quizList;

    String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Solve> completions;
}

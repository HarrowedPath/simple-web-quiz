package com.example.simplewebquiz.domain;

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

    @OneToMany(mappedBy = "user")
    List<Quiz> quizList;

    String role;
}

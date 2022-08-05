package com.example.simplewebquiz.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Lob
    @NotEmpty
    String title;

    @NotEmpty
    @Lob
    String text;

    @NotNull
    @ElementCollection
    @CollectionTable(name = "QUIZ_OPTIONS", joinColumns = @JoinColumn(name = "QUIZ_ID"))
    @Column(name = "QUIZ_OPTION")
    List<String> options;

    @ElementCollection
    @CollectionTable(
            name = "QUIZ_ANSWERS",
            joinColumns = @JoinColumn(name = "QUIZ_ID"))
    @Column(name = "QUIZ_ANSWER")
    List<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    User user;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    List<Solve> completions;
}

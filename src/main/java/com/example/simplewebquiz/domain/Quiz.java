package com.example.simplewebquiz.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "QUIZ")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Lob
    String title;
    @Lob
    String text;

    @ElementCollection
    @CollectionTable(name = "QUIZ_OPTIONS", joinColumns = @JoinColumn(name = "QUIZ_ID"))
    @Column(name = "QUIZ_OPTION")
    List<String> options;

    @ElementCollection
    @CollectionTable(
            name = "QUIZ_ANSWERS",
            joinColumns = @JoinColumn(name = "QUIZ_ID") )
    @Column(name = "QUIZ_ANSWER")
    List<Integer> answer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Quiz quiz = (Quiz) o;
        return id != null && Objects.equals(id, quiz.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

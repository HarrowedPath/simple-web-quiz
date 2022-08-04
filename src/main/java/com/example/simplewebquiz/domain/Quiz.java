package com.example.simplewebquiz.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

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

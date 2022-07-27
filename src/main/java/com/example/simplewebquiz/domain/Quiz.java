package com.example.simplewebquiz.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Quiz {
    long id;
    String title;
    String text;
    List<String> options;
    int answer;
}

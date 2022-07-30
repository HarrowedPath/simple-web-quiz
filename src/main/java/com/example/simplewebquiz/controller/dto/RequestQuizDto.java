package com.example.simplewebquiz.controller.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestQuizDto {
    @NotEmpty
    String title;
    @NotEmpty
    String text;
    @NotNull
    @Size(min = 2)
    List<String> options;
    List<Integer> answer;
}

package com.example.simplewebquiz.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseAnswerDto {

    @JsonProperty("success")
    Boolean status;

    String feedback;
}

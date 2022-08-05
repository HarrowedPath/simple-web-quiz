package com.example.simplewebquiz.controller.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @Email(regexp = "\\w+@\\w+\\.\\w+")
    @NotNull
    String email;

    @Size(min = 5, max = 255)
    String password;
}

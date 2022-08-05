package com.example.simplewebquiz.controller.mapper;

import com.example.simplewebquiz.controller.dto.UserDto;
import com.example.simplewebquiz.domain.User;
import com.example.simplewebquiz.domain.role.enums.UserRole;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    PasswordEncoder passwordEncoder;

    public User toModel(UserDto requestUserDto) {
        val user = new User();
        user.setRole(UserRole.ROLE_USER.name());
        user.setEmail(requestUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestUserDto.getPassword()));
        return user;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}

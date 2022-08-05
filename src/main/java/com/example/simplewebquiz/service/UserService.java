package com.example.simplewebquiz.service;

import com.example.simplewebquiz.domain.User;
import com.example.simplewebquiz.exception.UserAlreadyExistsException;
import com.example.simplewebquiz.exception.UserNotFoundException;
import com.example.simplewebquiz.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    public void registerNewUser(User user) {
        if (userRepository.existsById(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        userRepository.save(user);
    }

    public User findById(String email) {
        return userRepository
                .findById(email)
                .orElseThrow(UserNotFoundException::new);
    }
}

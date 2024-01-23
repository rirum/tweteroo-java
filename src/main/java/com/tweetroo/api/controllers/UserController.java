package com.tweetroo.api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.tweetroo.api.models.UserModel;
import com.tweetroo.api.repositories.UserRepository;

import lombok.NonNull;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users")

public class UserController {
    final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping

    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        try {
            UserModel newUser = userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

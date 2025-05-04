package com.stream.app.spring_stream_backend.user;

import com.stream.app.spring_stream_backend.user.dtos.CreateUserRequest;
import com.stream.app.spring_stream_backend.user.dtos.UserResponse;
import com.stream.app.spring_stream_backend.user.dtos.LoginUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final ModelMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

// converting the user entity to a DTO
    public UserResponse userResponseToDto(UserEntity user) {
        return mapper.map(user, UserResponse.class);
    }

    @PostMapping("")
    public ResponseEntity<UserResponse> signupUser(@Valid @RequestBody CreateUserRequest req) {
        logger.info("Creating user with username: {}", req.getUsername());
        logger.info("Creating user with email: {}", req.getEmail());
        UserEntity user = service.createUser(req);
        URI location = URI.create("/users/" + user.getId());
        return ResponseEntity.created(location).body(userResponseToDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginUserRequest req) {
        UserEntity user = service.loginUser(req);
        URI location = URI.create("/users/" + user.getId());
        logger.info("User logged in with username: {}", user.getUsername());
        return ResponseEntity.ok(mapper.map(user, UserResponse.class));
    }
}

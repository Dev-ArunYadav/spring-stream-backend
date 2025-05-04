package com.stream.app.spring_stream_backend.user;

import com.stream.app.spring_stream_backend.user.dtos.CreateUserRequest;
import com.stream.app.spring_stream_backend.user.dtos.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final ModelMapper mapper;

// converting the user entity to a DTO
    public CreateUserResponse userResponseToDto(UserEntity user) {
        return mapper.map(user, CreateUserResponse.class);
    }

    @PostMapping("")
    public ResponseEntity<CreateUserResponse> signupUser(@RequestBody CreateUserRequest req) {
        UserEntity user = service.createUser(req);
        URI location = URI.create("/users/" + user.getId());
        return ResponseEntity.created(location).body(userResponseToDto(user));
    }

    @PostMapping("/login")
    public void loginUser() {

    }
}

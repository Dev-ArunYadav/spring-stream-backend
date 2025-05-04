package com.stream.app.spring_stream_backend.user;

import com.stream.app.spring_stream_backend.user.dtos.CreateUserRequest;
import com.stream.app.spring_stream_backend.user.dtos.LoginUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserEntity createUser(CreateUserRequest req) {
        // Check if the user already exists
        Optional<UserEntity> existingUser = userRepository.findByUsername(req.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        // Check if the email already exists
        Optional<UserEntity> existingEmail = userRepository.findByEmail(req.getEmail());
        if (existingEmail.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        // Create a new user
        UserEntity map = mapper.map(req, UserEntity.class);
        return userRepository.save(map);
    }

    public UserEntity getUserById(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity getUserByUsername(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity loginUser(LoginUserRequest req) {
        UserEntity user = getUserByUsername(req.username());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
//        if (user.getPassword().equals(password)) {
//            return user;
//        } else {
//            throw new RuntimeException("Invalid credentials");
//        }
        return user;
    }
}

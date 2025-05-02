package com.stream.app.spring_stream_backend.user;

import com.stream.app.spring_stream_backend.user.dtos.CreateUserRequest;
import com.stream.app.spring_stream_backend.user.dtos.LoginUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // Add your service methods here

    public UserEntity createUser(CreateUserRequest req) {
        UserEntity user =  UserEntity.builder()
                .userName(req.username())
                .email(req.email())
//                .password(req.password())
                .build();
        return userRepository.save(user);
    }

    public UserEntity getUserById(Long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity getUserByUsername(String username) {
        Optional<UserEntity> userOptional = userRepository.findByUserName(username);
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

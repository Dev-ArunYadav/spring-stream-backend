package com.stream.app.spring_stream_backend.user;

import com.stream.app.spring_stream_backend.user.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired UserService userService;

    @Test
    @Order(1)
    public void testCreateUser() {
        UserEntity user = userService.createUser(
                new CreateUserRequest(
                        "Arun",
                        "ydv.arun@gmail.com",
                        "password"
                ));
        Assertions.assertNotNull(user);
        Assertions.assertEquals("Arun", user.getUsername());
    }
}

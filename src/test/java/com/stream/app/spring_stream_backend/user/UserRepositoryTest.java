package com.stream.app.spring_stream_backend.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository; // Field injection

    @Test
    @Order(1)
    void can_create_users() {
        UserEntity user = UserEntity.builder()
                .username("testUser")
                .email("ydv.arun@gmail.com")
                .bio("test bio")
                .imageUrl("test image url")
                .password("testPassword") // Assuming password is required
                .build();
        userRepository.save(user);
    }

    @Test
    @Order(2)
    void can_find_users(){
        UserEntity user = UserEntity.builder()
                .username("testUser")
                .email("ydv.arun@gmail.com")
                .bio("test bio")
                .imageUrl("test image url")
                .password("testPassword") // Assuming password is required
                .build();
        userRepository.save(user);
        var users = userRepository.findAll();
        assertEquals(1, users.size());
    }
}

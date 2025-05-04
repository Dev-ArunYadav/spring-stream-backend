package com.stream.app.spring_stream_backend.user.dtos;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String bio;
    private String imageUrl;
}
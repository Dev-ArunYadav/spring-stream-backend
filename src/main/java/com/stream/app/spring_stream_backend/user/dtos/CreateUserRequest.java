package com.stream.app.spring_stream_backend.user.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.annotation.Nonnull;

public record CreateUserRequest(
        @Nonnull
        String username,
        @Nonnull
        String email,
        @Nonnull
        String password
) {
    @JsonCreator
    public CreateUserRequest {
        // Constructor for JSON deserialization
    }
}

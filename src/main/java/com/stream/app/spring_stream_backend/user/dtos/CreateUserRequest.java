package com.stream.app.spring_stream_backend.user.dtos;

import jakarta.annotation.Nonnull;

public record CreateUserRequest(
        @Nonnull
        String username,
        @Nonnull
        String email,
        @Nonnull
        String password
) {
}

package com.stream.app.spring_stream_backend.user.dtos;

import jakarta.annotation.Nonnull;

public record LoginUserRequest (
        @Nonnull
        String username,
        @Nonnull
        String password
) {
}

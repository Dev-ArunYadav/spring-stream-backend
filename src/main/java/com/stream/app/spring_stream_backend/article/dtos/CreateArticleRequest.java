package com.stream.app.spring_stream_backend.article.dtos;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public record CreateArticleRequest(
        @Nonnull
        String title,
        @Nonnull
        String body,
        @Nullable
        String subtitle

) {
}

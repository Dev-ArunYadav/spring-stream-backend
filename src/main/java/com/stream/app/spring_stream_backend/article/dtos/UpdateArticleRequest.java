package com.stream.app.spring_stream_backend.article.dtos;

import jakarta.annotation.Nullable;

public record UpdateArticleRequest(
        @Nullable
        String title,
        @Nullable
        String body,
        @Nullable
        String subtitle
) {
}

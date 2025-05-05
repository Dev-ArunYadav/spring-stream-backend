package com.stream.app.spring_stream_backend.article.dtos;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateArticleRequest {
        @NotBlank
        private String title;
        @NotBlank
        private String body;
        @Nullable
        private String subtitle;
}

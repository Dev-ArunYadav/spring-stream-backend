package com.stream.app.spring_stream_backend.comments.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private String authorUsername;
}

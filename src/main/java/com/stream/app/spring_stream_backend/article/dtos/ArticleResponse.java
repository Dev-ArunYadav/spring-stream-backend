package com.stream.app.spring_stream_backend.article.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse{
    private Long id;
    private String title;
    private String subtitle;
    private String slug;
    private String body;
    private LocalDateTime createdAt;
    private String authorUsername ;
}
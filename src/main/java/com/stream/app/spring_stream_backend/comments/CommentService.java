package com.stream.app.spring_stream_backend.comments;

import com.stream.app.spring_stream_backend.article.ArticleEntity;
import com.stream.app.spring_stream_backend.article.ArticleRepository;
import com.stream.app.spring_stream_backend.comments.dtos.CreateCommentRequest;
import com.stream.app.spring_stream_backend.user.UserEntity;
import com.stream.app.spring_stream_backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public CommentEntity addComment(String slug, Long authorId, CreateCommentRequest req) {
        ArticleEntity article = articleRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        UserEntity user = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CommentEntity comment = CommentEntity.builder()
                .title(req.getTitle())
                .body(req.getBody())
                .author(user)
                .article(article)
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }
}

package com.stream.app.spring_stream_backend.article;

import com.stream.app.spring_stream_backend.article.dtos.CreateArticleRequest;
import com.stream.app.spring_stream_backend.article.dtos.UpdateArticleRequest;
import com.stream.app.spring_stream_backend.user.UserEntity;
import com.stream.app.spring_stream_backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleEntity createArticle(CreateArticleRequest req, Long authorId) {
        articleRepository.findBySlug(req.getTitle().toLowerCase().replaceAll(" ", "-"))
                .ifPresent(article -> {
                    throw new RuntimeException("Article with this slug already exists");
                });
        // Check if the author exists
        UserEntity author = userRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        ArticleEntity article = ArticleEntity.builder()
                .title(req.getTitle())
                .slug(req.getTitle().toLowerCase().replaceAll(" ", "-"))
                .body(req.getBody())
                .subtitle(req.getSubtitle())
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();
        return articleRepository.save(article);
    }

    public ArticleEntity getArticleBySlug(String slug) {
        Optional<ArticleEntity> articleEntityOptional =  articleRepository.findBySlug(slug);
            return articleEntityOptional.orElseThrow(() -> new RuntimeException("Article not found"));
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }

    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest req) {
        ArticleEntity article = articleRepository.findById(articleId)
                                    .orElseThrow(() -> new RuntimeException("Article not found"));
        if (req.getTitle() != null) {
            article.setTitle(req.getTitle());
            article.setSlug(req.getTitle().toLowerCase().replaceAll(" ", "-"));
        }
        if (req.getSubtitle() != null) {
            article.setSubtitle(req.getSubtitle());
        }
        if (req.getBody() != null) {
            article.setBody(req.getBody());
        }
        article.setCreatedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}

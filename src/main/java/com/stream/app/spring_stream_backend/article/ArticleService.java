package com.stream.app.spring_stream_backend.article;

import com.stream.app.spring_stream_backend.article.dtos.CreateArticleRequest;
import com.stream.app.spring_stream_backend.article.dtos.UpdateArticleRequest;
import com.stream.app.spring_stream_backend.user.UserEntity;
import com.stream.app.spring_stream_backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleEntity createArticle(CreateArticleRequest req, Long authorId) {
        // Check if the author exists
        UserEntity author = userRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found"));
        ArticleEntity article = ArticleEntity.builder()
                .title(req.title())
                .slug(req.title().toLowerCase().replaceAll(" ", "-"))
                .body(req.body())
                .subtitle(req.subtitle())
                .author(author)
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
        if (req.title() != null) {
            article.setTitle(req.title());
            article.setSlug(req.title().toLowerCase().replaceAll(" ", "-"));
        }
        if (req.subtitle() != null) {
            article.setSubtitle(req.subtitle());
        }
        if (req.body() != null) {
            article.setBody(req.body());
        }
        return articleRepository.save(article);
    }

    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}

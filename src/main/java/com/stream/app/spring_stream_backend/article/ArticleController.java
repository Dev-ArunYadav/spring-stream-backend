package com.stream.app.spring_stream_backend.article;

import com.stream.app.spring_stream_backend.article.dtos.ArticleResponse;
import com.stream.app.spring_stream_backend.article.dtos.CreateArticleRequest;
import com.stream.app.spring_stream_backend.article.dtos.UpdateArticleRequest;
import com.stream.app.spring_stream_backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @PostMapping("")
    public ResponseEntity<ArticleResponse> createArticle(
            @RequestBody CreateArticleRequest req,
            @RequestParam Long authorId  // In real-world apps,
            // this should be from auth context ( @AuthenticationPrincipal UserDetails userDetails )
    ) {
        ArticleEntity article = articleService.createArticle(req, authorId);
        ArticleResponse articleResponse = mapper.map(article, ArticleResponse.class);
        URI location = URI.create("/article/" + article.getId());
        return ResponseEntity.created(location).body(articleResponse);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ArticleResponse> getArticleBySlug(@PathVariable String slug) {
        ArticleEntity article = articleService.getArticleBySlug(slug);
        ArticleResponse articleResponse = mapper.map(article, ArticleResponse.class);
        return ResponseEntity.ok(articleResponse);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ArticleResponse>> getAllArticles() {
        Iterable<ArticleEntity> articles = articleService.getAllArticles();
        Iterable<ArticleResponse> articleResponses = List.of(mapper.map(articles, ArticleResponse[].class));
        return ResponseEntity.ok(articleResponses);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long articleId,
            @RequestBody UpdateArticleRequest req
    ) {
        ArticleEntity article = articleService.updateArticle(articleId, req);
        ArticleResponse articleResponse = mapper.map(article, ArticleResponse.class);
        return ResponseEntity.ok(articleResponse);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }

}

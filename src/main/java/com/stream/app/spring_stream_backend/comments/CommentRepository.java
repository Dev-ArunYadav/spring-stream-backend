package com.stream.app.spring_stream_backend.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Iterable<CommentEntity> findAllByArticle_Id(Long articleId);

    Iterable<CommentEntity> findAllByAuthor_Id(Long userId);

//    Iterable<CommentEntity> findAllByArticleSlug(String slug);

//    Iterable<CommentEntity> findAllByArticleSlugAndAuthor_Id(String slug, Long userId);
}

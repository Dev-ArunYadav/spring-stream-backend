package com.stream.app.spring_stream_backend.comments;

import com.stream.app.spring_stream_backend.comments.dtos.CreateCommentRequest;
import com.stream.app.spring_stream_backend.comments.dtos.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/article/{slug}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final ModelMapper mapper;

    @PostMapping("")
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable String slug,
            @RequestBody CreateCommentRequest req,
            @RequestParam Long authorId // Ideally from authentication context
    ) {
        CommentEntity comment = commentService.addComment(slug, authorId, req);
        URI location = URI.create("/article/" + slug + "/comments/" + comment.getId());
        return ResponseEntity.created(location).body(mapper.map(comment, CommentResponse.class));
    }
}
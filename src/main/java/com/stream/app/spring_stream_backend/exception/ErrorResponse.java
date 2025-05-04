package com.stream.app.spring_stream_backend.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private String error;
    private Object details;
    private int status;
    private LocalDateTime timestamp;
}

package com.stream.app.spring_stream_backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException extends RuntimeException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        ErrorResponse internalServerError = ErrorResponse.builder()
                .message(e.getMessage())
                .error("Internal Server Error")
                .status(500)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(500)
                .body(internalServerError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e){
        ErrorResponse badRequest = ErrorResponse.builder()
                .message(e.getMessage())
                .error("Bad Request")
                .status(400)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(400)
                .body(badRequest);
    }


}

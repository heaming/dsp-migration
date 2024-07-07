package com.ad.migration.internal.api.exception;

import com.ad.migration.application.user.AlreadyAgreedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(AlreadyAgreedException.class)
    public ResponseEntity<?> handleAlreadyAgreedException() {
        return ResponseEntity.badRequest().build();
    }
}

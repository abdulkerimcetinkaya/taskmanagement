package com.example.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private Map<String, String> errors;
}

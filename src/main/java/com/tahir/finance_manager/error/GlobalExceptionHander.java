package com.tahir.finance_manager.error;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHander {
  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException ex) {
    ApiError apiError = new ApiError("username not found with username: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
    ApiError apiError = new ApiError("Authentication faild: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }

  @ExceptionHandler(JwtException.class)
  public ResponseEntity<ApiError> handleUsernameNotFoundException(JwtException ex) {
    ApiError apiError = new ApiError("Invalid JWT token: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiError> handleUsernameNotFoundException(AccessDeniedException ex) {
    ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.FORBIDDEN);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleUsernameNotFoundException(Exception ex) {
    ApiError apiError = new ApiError("An unexpected error occurred: " + ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    ApiError apiError = new ApiError("An unexpected error occurred: " + ex.getMessage(),
        HttpStatus.BAD_REQUEST, errors);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<ApiError> handleResponseStatusException(ResponseStatusException ex) {
    ApiError apiError = new ApiError(ex.getReason(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleMissingRequestBody(HttpMessageNotReadableException ex) {
    ApiError apiError = new ApiError("Request body is required", HttpStatus.BAD_REQUEST);
    if (ex.getMessage().contains("ArrayList") && ex.getMessage().contains("Long")) {
            apiError.setError("Invalid format for 'expenseTypes'. It must be an array of numbers, e.g. [1, 2, 3]");
        } else {
          apiError.setError("Malformed JSON request");
        }
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
    ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
    ApiError apiError = new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }
}
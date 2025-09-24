package com.tahir.finance_manager.error;

import java.nio.file.AccessDeniedException;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.JwtException;

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
    ApiError apiError = new ApiError("Access denied: Insufficient permissions", HttpStatus.FORBIDDEN);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleUsernameNotFoundException(Exception ex) {
    ApiError apiError = new ApiError("An unexpected error occurred: " + ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(apiError, apiError.getStatusCode());
  }
}
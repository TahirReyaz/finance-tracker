package com.tahir.finance_manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.finance_manager.dto.LoginRequestDto;
import com.tahir.finance_manager.dto.LoginResponseDto;
import com.tahir.finance_manager.dto.SignupRequestDto;
import com.tahir.finance_manager.dto.SignupResponseDto;
import com.tahir.finance_manager.security.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping(path = "/login")
  public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
    return ResponseEntity.ok(authService.login(loginRequestDto));
  }

  @PostMapping(path = "/signup")
  public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
    return ResponseEntity.ok(authService.signup(signupRequestDto));

  }

}

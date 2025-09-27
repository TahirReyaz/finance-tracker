package com.tahir.finance_manager.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tahir.finance_manager.dto.LoginRequestDto;
import com.tahir.finance_manager.dto.LoginResponseDto;
import com.tahir.finance_manager.dto.SignupRequestDto;
import com.tahir.finance_manager.dto.SignupResponseDto;
import com.tahir.finance_manager.entities.User;
import com.tahir.finance_manager.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final AuthUtil authUtil;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public LoginResponseDto login(LoginRequestDto loginRequestDto) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));

    User user = (User) authentication.getPrincipal();

    String token = authUtil.generateAccessToken(user);

    return new LoginResponseDto(token, user.getId());
  }

  public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
    User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

    user = userRepository.findByEmail(signupRequestDto.getEmail()).orElse(user);

    if (user != null)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or email already exists");

    user = userRepository.save(User.builder()
        .username(signupRequestDto.getUsername())
        .password(passwordEncoder.encode(signupRequestDto.getPassword()))
        .email(signupRequestDto.getEmail())
        .build());

    return new SignupResponseDto(user.getId(), user.getUsername());
  }
}

package com.tahir.finance_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequestDto {
  @NotBlank(message = "Username is required")
  @Size(min = 3, max = 20, message = "Username must be 3-20 characters")
  private String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Use a valid email format")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 30, message = "Password must be 8-30 characters")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
  private String password;
}

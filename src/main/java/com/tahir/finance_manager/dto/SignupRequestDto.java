package com.tahir.finance_manager.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
  private String username;
  private String email;
  private String password;
}

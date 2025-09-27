package com.tahir.finance_manager.dto.expenseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateResponseDto {
  private String id;
  private String name;
}

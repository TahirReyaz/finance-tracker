package com.tahir.finance_manager.dto.expenseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExpenseTypeResponseDto {
  private Long id;
  private String name;
}

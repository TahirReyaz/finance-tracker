package com.tahir.finance_manager.dto.expenseType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateExpenseTypeRequestDto {
  @NotBlank(message = "Name is required")
  @Size(min = 3, max = 20, message = "Name must be 3-20 characters")
  private String name;
}

package com.tahir.finance_manager.dto.expense;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateExpenseRequestDto {
  @NotBlank(message = "Item name is required")
  @Size(min = 3, max = 20, message = "Item name must be 3-20 characters")
  private String item;

  @NotNull(message = "Time is required")
  @PastOrPresent(message = "Time must not be in future")
  private Timestamp time;

  @NotNull(message = "User is required")
  private Long user;

  @JsonProperty("expense_type")
  @NotNull(message = "Expense Type is required")
  private Long expenseType;

  @JsonProperty("expense_group")
  private Long expense_group;

  @NotNull(message = "Amount is required")
  @Positive(message = "Amount must greater than 0")
  private BigDecimal amount;
}

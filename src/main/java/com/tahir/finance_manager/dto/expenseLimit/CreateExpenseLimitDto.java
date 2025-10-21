package com.tahir.finance_manager.dto.expenseLimit;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateExpenseLimitDto {
  @JsonProperty("expenseType")
  @NotNull(message = "Expense Type is required")
  private Long expenseType;

  @NotNull(message = "Time is required")
  @PastOrPresent(message = "Time must not be in future")
  private Timestamp time;

  @NotNull(message = "Amount is required")
  @Positive(message = "Amount must greater than 0")
  private BigDecimal amount;
}

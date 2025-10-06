package com.tahir.finance_manager.dto.expense;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.tahir.finance_manager.entities.ExpenseGroup;
import com.tahir.finance_manager.entities.ExpenseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponseDto {
  private Long id;
  private String item;
  private Long user;
  private ExpenseType expense_type;
  private ExpenseGroup expense_group;
  private Timestamp time;
  private BigDecimal amount;
}

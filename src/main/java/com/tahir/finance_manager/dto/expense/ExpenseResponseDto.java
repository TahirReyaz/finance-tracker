package com.tahir.finance_manager.dto.expense;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.tahir.finance_manager.entities.ExpenseGroup;
import com.tahir.finance_manager.entities.ExpenseType;
import com.tahir.finance_manager.entities.Expense;

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
  private ExpenseType expenseType;
  private ExpenseGroup expense_group;
  private Timestamp time;
  private BigDecimal amount;

  public ExpenseResponseDto(Expense expense) {
    if (expense == null) {
      this.id = null;
      this.item = null;
      this.user = null;
      this.expenseType = null;
      this.expense_group = null;
      this.time = null;
      this.amount = null;
      return;
    }

    this.id = expense.getId();
    this.item = expense.getItem();
    this.user = expense.getUser().getId();
    this.expenseType = expense.getExpenseType();
    this.expense_group = expense.getExpense_group();
    this.time = expense.getTime();
    this.amount = expense.getAmount();
  }

}

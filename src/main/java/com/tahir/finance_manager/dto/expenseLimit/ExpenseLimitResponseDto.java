package com.tahir.finance_manager.dto.expenseLimit;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.tahir.finance_manager.entities.ExpenseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ExpenseLimitResponseDto {
    private Long id;
    private Long userId;
    private ExpenseType expenseType;
    private BigDecimal amount;
    private Timestamp time;
    private Timestamp created_at;
    private Timestamp updated_at;
}

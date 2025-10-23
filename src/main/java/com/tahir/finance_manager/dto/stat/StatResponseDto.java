package com.tahir.finance_manager.dto.stat;

import java.math.BigDecimal;
import java.util.List;

import com.tahir.finance_manager.dto.expense.ExpenseResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatResponseDto {
    private Integer totalExpenses;
    private BigDecimal totalAmount;
    private List<ExpenseResponseDto> expenses;
}

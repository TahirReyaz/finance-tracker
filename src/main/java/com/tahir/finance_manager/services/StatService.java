package com.tahir.finance_manager.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tahir.finance_manager.dto.expense.ExpenseResponseDto;
import com.tahir.finance_manager.dto.stat.StatRequestDto;
import com.tahir.finance_manager.dto.stat.StatResponseDto;
import com.tahir.finance_manager.entities.Expense;
import com.tahir.finance_manager.entities.User;
import com.tahir.finance_manager.repositories.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatService {
    private final ExpenseService expenseService;
    private final ExpenseRepository expenseRepository;

    public StatResponseDto getStats (StatRequestDto statRequestDto) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Expense> filteredExpenses = expenseRepository.findByUserIdAndExpenseTypeIdIn(
            loggedUser.getId(),
            statRequestDto.getExpenseTypes()
        ).orElse(null);

        BigDecimal totalAmount = BigDecimal.ZERO;
        if (filteredExpenses != null) {
            for (Expense expense : filteredExpenses) {
                totalAmount = totalAmount.add(expense.getAmount());
            }
        }

        List<ExpenseResponseDto> expenseDtos = filteredExpenses.stream()
            .map(expense -> new ExpenseResponseDto(expense))
            .toList();

        return new StatResponseDto(filteredExpenses.size(), totalAmount, expenseDtos
        );
    }
}

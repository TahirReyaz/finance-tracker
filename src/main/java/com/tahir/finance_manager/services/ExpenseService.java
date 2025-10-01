package com.tahir.finance_manager.services;

import org.springframework.stereotype.Service;

import com.tahir.finance_manager.dto.expense.CreateExpenseRequestDto;
import com.tahir.finance_manager.dto.expense.CreateExpenseResponseDto;
import com.tahir.finance_manager.entities.Expense;
import com.tahir.finance_manager.entities.ExpenseGroup;
import com.tahir.finance_manager.entities.ExpenseType;
import com.tahir.finance_manager.entities.User;
import com.tahir.finance_manager.repositories.ExpenseGroupRepository;
import com.tahir.finance_manager.repositories.ExpenseRepository;
import com.tahir.finance_manager.repositories.ExpenseTypeRepository;
import com.tahir.finance_manager.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {
        private final ExpenseRepository expenseRepository;
        private final ExpenseTypeRepository expenseTypeRepository;
        private final ExpenseGroupRepository expenseGroupRepository;
        private final UserRepository userRepository;

        public CreateExpenseResponseDto getExpenseDetails(Long id) {
                Expense expense = expenseRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Expense not found"));

                return new CreateExpenseResponseDto(expense.getId(), expense.getItem(),
                                expense.getUser().getId(),
                                expense.getExpense_type(), expense.getExpense_group(), expense.getTime(),
                                expense.getAmount());
        }

        public CreateExpenseResponseDto createExpense(CreateExpenseRequestDto createRequestDto) {
                ExpenseType expenseType = expenseTypeRepository.findById(createRequestDto.getExpenseType())
                                .orElseThrow(() -> new IllegalArgumentException("Expense type not found"));

                ExpenseGroup expenseGroup = (createRequestDto.getExpense_group() != null)
                                ? expenseGroupRepository.findById(createRequestDto.getExpense_group())
                                                .orElseThrow(() -> new IllegalArgumentException(
                                                                "Expense group not found"))
                                : null;

                User user = userRepository.findById(createRequestDto.getUser())
                                .orElseThrow(() -> new IllegalArgumentException("User not found"));

                Expense newExpense = Expense.builder()
                                .item(createRequestDto.getItem())
                                .amount(createRequestDto.getAmount())
                                .expense_type(expenseType)
                                .expense_group(expenseGroup)
                                .time(createRequestDto.getTime())
                                .user(user)
                                .build();

                Expense savedExpense = expenseRepository.save(newExpense);

                return new CreateExpenseResponseDto(savedExpense.getId(), savedExpense.getItem(),
                                savedExpense.getUser().getId(),
                                savedExpense.getExpense_type(), savedExpense.getExpense_group(), savedExpense.getTime(),
                                savedExpense.getAmount());
        }
}

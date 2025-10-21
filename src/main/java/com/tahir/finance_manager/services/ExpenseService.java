package com.tahir.finance_manager.services;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tahir.finance_manager.dto.expense.CreateExpenseRequestDto;
import com.tahir.finance_manager.dto.expense.ExpenseResponseDto;
import com.tahir.finance_manager.entities.Expense;
import com.tahir.finance_manager.entities.ExpenseGroup;
import com.tahir.finance_manager.entities.ExpenseType;
import com.tahir.finance_manager.entities.User;
import com.tahir.finance_manager.repositories.ExpenseGroupRepository;
import com.tahir.finance_manager.repositories.ExpenseRepository;
import com.tahir.finance_manager.repositories.ExpenseTypeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseService {
        private final ExpenseRepository expenseRepository;
        private final ExpenseTypeRepository expenseTypeRepository;
        private final ExpenseGroupRepository expenseGroupRepository;

        public ExpenseResponseDto getExpenseDetails(Long id) throws AccessDeniedException {
                Expense expense = expenseRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Expense not found"));
                User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (loggedUser.getId() != expense.getUser().getId()) {
                        throw new AccessDeniedException("You are not the owner of this expense");
                }

                return new ExpenseResponseDto(expense.getId(), expense.getItem(),
                                expense.getUser().getId(),
                                expense.getExpenseType(), expense.getExpense_group(), expense.getTime(),
                                expense.getAmount());
        }

        public ExpenseResponseDto createExpense(CreateExpenseRequestDto createRequestDto) {
                ExpenseType expenseType = expenseTypeRepository.findById(createRequestDto.getExpenseType())
                                .orElseThrow(() -> new IllegalArgumentException("Expense type not found"));

                ExpenseGroup expenseGroup = (createRequestDto.getExpense_group() != null)
                                ? expenseGroupRepository.findById(createRequestDto.getExpense_group())
                                                .orElseThrow(() -> new IllegalArgumentException(
                                                                "Expense group not found"))
                                : null;

                User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                Expense newExpense = Expense.builder()
                                .item(createRequestDto.getItem())
                                .amount(createRequestDto.getAmount())
                                .expenseType(expenseType)
                                .expense_group(expenseGroup)
                                .time(createRequestDto.getTime())
                                .user(loggedUser)
                                .build();

                Expense savedExpense = expenseRepository.save(newExpense);

                return new ExpenseResponseDto(savedExpense.getId(), savedExpense.getItem(),
                                savedExpense.getUser().getId(),
                                savedExpense.getExpenseType(), savedExpense.getExpense_group(), savedExpense.getTime(),
                                savedExpense.getAmount());
        }

        public List<ExpenseResponseDto> getMyExpenses() {
                User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                List<Expense> myExpenses =  expenseRepository.findByUserId(loggedUser.getId())
                        .orElseThrow(() -> new EntityNotFoundException("No expenses found for the user"));

                List<ExpenseResponseDto> myExpensesDto = myExpenses.stream().map(expense -> new ExpenseResponseDto(
                                expense.getId(),
                                expense.getItem(),
                                expense.getUser().getId(),
                                expense.getExpenseType(),
                                expense.getExpense_group(),
                                expense.getTime(),
                                expense.getAmount())).toList();

                return myExpensesDto;
        }
}

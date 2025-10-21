package com.tahir.finance_manager.services;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tahir.finance_manager.dto.expenseLimit.CreateExpenseLimitDto;
import com.tahir.finance_manager.dto.expenseLimit.ExpenseLimitResponseDto;
import com.tahir.finance_manager.entities.ExpenseLimit;
import com.tahir.finance_manager.entities.ExpenseType;
import com.tahir.finance_manager.entities.User;
import com.tahir.finance_manager.repositories.ExpenseLimitRepository;
import com.tahir.finance_manager.repositories.ExpenseTypeRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseLimitService {
    private final ExpenseLimitRepository expenseLimitRepository;
    private final ExpenseTypeRepository expenseTypeRepository;

    public List<ExpenseLimitResponseDto> getAllExpenseLimits() {
        List<ExpenseLimit> expenseLimits = (List<ExpenseLimit>) expenseLimitRepository.findAll();
        return expenseLimits.stream()
                .map(el -> new ExpenseLimitResponseDto(el.getId(), el.getUser().getId(), el.getExpenseType(), el.getAmount(), el.getTime(), el.getCreated_at(), el.getUpdated_at()))
                .toList();
    }

    public List<ExpenseLimitResponseDto> getExpenseLimitByUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ExpenseLimit> expenseLimits = expenseLimitRepository.findByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException("No Expense Limit found for user: " + user.getUsername()));
        List<ExpenseLimitResponseDto> expenseLimitDtos = expenseLimits.stream()
                .map(el -> new ExpenseLimitResponseDto(el.getId(), el.getUser().getId(), el.getExpenseType(), el.getAmount(), el.getTime(), el.getCreated_at(), el.getUpdated_at()))
                .toList();
                return expenseLimitDtos;
    }

    public ExpenseLimitResponseDto createExpenseLimit(CreateExpenseLimitDto createExpenseLimitDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ExpenseType expenseType = expenseTypeRepository.findById(createExpenseLimitDto.getExpenseType())
                .orElseThrow(() -> new EntityNotFoundException("Given Expense type not found: " + createExpenseLimitDto.getExpenseType()));

        ExpenseLimit newExpenseLimit = ExpenseLimit.builder()
                .user(user)
                .expenseType(expenseType)
                .amount(createExpenseLimitDto.getAmount())
                .time(createExpenseLimitDto.getTime())
                .build();
        ExpenseLimit savedExpenseLimit = expenseLimitRepository.save(newExpenseLimit);
        return new ExpenseLimitResponseDto(savedExpenseLimit.getId(), savedExpenseLimit.getUser().getId(), savedExpenseLimit.getExpenseType(), savedExpenseLimit.getAmount(), savedExpenseLimit.getTime(), savedExpenseLimit.getCreated_at(), savedExpenseLimit.getUpdated_at());
    }

    public ExpenseLimitResponseDto getExpenseLimitDetails(Long id) throws AccessDeniedException {
        ExpenseLimit expenseLimit = expenseLimitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense limit not found with id: " + id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (expenseLimit.getUser().getId() != user.getId()) {
            throw new AccessDeniedException("You are not the owner of this expense limit");
        }
        return new ExpenseLimitResponseDto(expenseLimit.getId(), expenseLimit.getUser().getId(), expenseLimit.getExpenseType(), expenseLimit.getAmount(),expenseLimit.getTime(), expenseLimit.getCreated_at(), expenseLimit.getUpdated_at());
    }

}

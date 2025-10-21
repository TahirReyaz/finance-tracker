package com.tahir.finance_manager.controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.finance_manager.dto.expenseLimit.CreateExpenseLimitDto;
import com.tahir.finance_manager.dto.expenseLimit.ExpenseLimitResponseDto;
import com.tahir.finance_manager.services.ExpenseLimitService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/expense-limit")
@RequiredArgsConstructor
public class ExpenseLimitController {
    private final ExpenseLimitService expenseLimitService;

    @GetMapping("/all")
    public List<ExpenseLimitResponseDto> getAllExpenseLimits() {
        return expenseLimitService.getAllExpenseLimits();
    }

    @GetMapping
    public List<ExpenseLimitResponseDto> getMyExpenseLimits() {
        return expenseLimitService.getExpenseLimitByUserId();
    }

    @GetMapping("/{id}")
    public ExpenseLimitResponseDto getExpenseLimitDetails(@PathVariable Long id) throws AccessDeniedException {
        return expenseLimitService.getExpenseLimitDetails(id);
    }

    @PostMapping
    public ExpenseLimitResponseDto createExpenseLimit (@Valid @RequestBody CreateExpenseLimitDto createExpenseLimitDto) {
        return expenseLimitService.createExpenseLimit(createExpenseLimitDto);
    }
}

package com.tahir.finance_manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.finance_manager.dto.expense.CreateExpenseRequestDto;
import com.tahir.finance_manager.dto.expense.CreateExpenseResponseDto;
import com.tahir.finance_manager.entities.Expense;
import com.tahir.finance_manager.repositories.ExpenseRepository;
import com.tahir.finance_manager.services.ExpenseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
public class ExpenseController {
  private final ExpenseRepository expenseRepository;
  private final ExpenseService expenseService;

  @GetMapping("/all")
  public @ResponseBody Iterable<Expense> getAllExpenses() {
    return expenseRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<CreateExpenseResponseDto> createExpense(
      @Valid @RequestBody CreateExpenseRequestDto createExpenseRequestDto) {
    return ResponseEntity.ok(expenseService.createExpense(createExpenseRequestDto));
  }

}

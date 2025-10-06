package com.tahir.finance_manager.controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.finance_manager.dto.expense.CreateExpenseRequestDto;
import com.tahir.finance_manager.dto.expense.ExpenseResponseDto;
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
  public ResponseEntity<ExpenseResponseDto> createExpense(
      @Valid @RequestBody CreateExpenseRequestDto createExpenseRequestDto) {
    return ResponseEntity.ok(expenseService.createExpense(createExpenseRequestDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExpenseResponseDto> getExpense(@PathVariable Long id) throws AccessDeniedException {
    return ResponseEntity.ok(expenseService.getExpenseDetails(id));
  }

  @GetMapping
  public @ResponseBody List<ExpenseResponseDto> getMyExpenses() {
    return expenseService.getMyExpenses();
  }

}

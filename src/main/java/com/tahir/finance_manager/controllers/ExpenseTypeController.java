package com.tahir.finance_manager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.finance_manager.dto.expenseType.CreateRequestDto;
import com.tahir.finance_manager.dto.expenseType.CreateResponseDto;
import com.tahir.finance_manager.entities.ExpenseType;
import com.tahir.finance_manager.repositories.ExpenseTypeRepository;
import com.tahir.finance_manager.services.ExpenseTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping(path = "/expense-type")
@RequiredArgsConstructor
public class ExpenseTypeController {
  private final ExpenseTypeRepository expenseTypeRepository;
  private final ExpenseTypeService expenseTypeService;

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<ExpenseType> getAllExpenseTypes() {
    return expenseTypeRepository.findAll();
  }

  @PostMapping
  public ResponseEntity<CreateResponseDto> createType(@Valid @RequestBody CreateRequestDto createRequestDto) {
    return ResponseEntity.ok(expenseTypeService.createType(createRequestDto));
  }

}

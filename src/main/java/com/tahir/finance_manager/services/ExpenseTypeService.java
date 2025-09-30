package com.tahir.finance_manager.services;

import org.springframework.stereotype.Service;

import com.tahir.finance_manager.dto.expenseType.CreateExpenseTypeRequestDto;
import com.tahir.finance_manager.dto.expenseType.CreateExpenseTypeResponseDto;
import com.tahir.finance_manager.entities.ExpenseType;
import com.tahir.finance_manager.repositories.ExpenseTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseTypeService {
  private final ExpenseTypeRepository expenseTypeRepository;

  public CreateExpenseTypeResponseDto createType(CreateExpenseTypeRequestDto createRequestDto) {
    ExpenseType expenseType = expenseTypeRepository.findByName(
        createRequestDto.getName()).orElse(null);

    if (expenseType != null) {
      throw new IllegalArgumentException("Type already exists");
    }

    expenseType = expenseTypeRepository.save(
        ExpenseType.builder()
            .name(createRequestDto.getName())
            .build());

    return new CreateExpenseTypeResponseDto(expenseType.getId(), expenseType.getName());
  }
}

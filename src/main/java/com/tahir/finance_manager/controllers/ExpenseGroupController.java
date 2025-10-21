package com.tahir.finance_manager.controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.finance_manager.dto.expenseGroup.CreateExpenseGroupRequestDto;
import com.tahir.finance_manager.dto.expenseGroup.ExpenseGroupResponseDto;
import com.tahir.finance_manager.services.ExpenseGroupService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/expense-group")
@AllArgsConstructor
public class ExpenseGroupController {
    ExpenseGroupService expenseGroupService;

    @GetMapping("/all")
    public List<ExpenseGroupResponseDto> getAllExpenseGroups() {
        return expenseGroupService.getAllExpenseGroups();
    }

    @GetMapping("/{id}")
    public ExpenseGroupResponseDto getExpenseGroupDetails(@PathVariable Long id) throws AccessDeniedException {
        return expenseGroupService.getExpenseGroupDetails(id);
    }

    @GetMapping
    public List<ExpenseGroupResponseDto> getMyExpenseGroups() {
        return expenseGroupService.getMyExpenseGroups();
    }

    @PostMapping
    public ExpenseGroupResponseDto createExpenseGroup (@Valid @RequestBody CreateExpenseGroupRequestDto createExpenseGroupRequestDto) {
        return expenseGroupService.createExpenseGroup(createExpenseGroupRequestDto);
    }
}

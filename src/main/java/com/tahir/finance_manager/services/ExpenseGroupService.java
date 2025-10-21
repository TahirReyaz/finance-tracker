package com.tahir.finance_manager.services;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tahir.finance_manager.dto.expenseGroup.CreateExpenseGroupRequestDto;
import com.tahir.finance_manager.dto.expenseGroup.ExpenseGroupResponseDto;
import com.tahir.finance_manager.entities.ExpenseGroup;
import com.tahir.finance_manager.entities.User;
import com.tahir.finance_manager.repositories.ExpenseGroupRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseGroupService {
    private final ExpenseGroupRepository expenseGroupRepository;

    public ExpenseGroupResponseDto createExpenseGroup(CreateExpenseGroupRequestDto createExpenseGroupRequestDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ExpenseGroup expenseGroup = expenseGroupRepository.findByName(createExpenseGroupRequestDto.getName()).orElse(null);

        if(expenseGroup != null && expenseGroup.getUser().getId() == user.getId()) {
            throw new IllegalArgumentException("Group with same name already exists for this user!");
        }

        ExpenseGroup newExpenseGroup = ExpenseGroup.builder()
                .name(createExpenseGroupRequestDto.getName())
                .user(user)
                .build();
                ExpenseGroup savedExpenseGroup = expenseGroupRepository.save(newExpenseGroup);
                return new ExpenseGroupResponseDto(savedExpenseGroup.getId(), savedExpenseGroup.getName(),
                        savedExpenseGroup.getUser().getId(), savedExpenseGroup.getCreated_at(), savedExpenseGroup.getUpdated_at());   
    }

    public List<ExpenseGroupResponseDto> getMyExpenseGroups() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ExpenseGroup> expenseGroups = expenseGroupRepository.findByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException("No expense groups found for this user"));
        return expenseGroups.stream()
                .map(eg -> new ExpenseGroupResponseDto(eg.getId(), eg.getName(), eg.getUser().getId(), eg.getCreated_at(), eg.getUpdated_at()))
                .toList();
    }

    public ExpenseGroupResponseDto getExpenseGroupDetails(Long id) throws AccessDeniedException {
        ExpenseGroup expenseGroup = expenseGroupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Expense group not found"));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (expenseGroup.getUser().getId() != user.getId()) {
            throw new AccessDeniedException("You are not the owner of this expense group");
        }
        return new ExpenseGroupResponseDto(expenseGroup.getId(), expenseGroup.getName(), expenseGroup.getUser().getId(), expenseGroup.getCreated_at(), expenseGroup.getUpdated_at());
    }

    public List<ExpenseGroupResponseDto> getAllExpenseGroups() {
        List<ExpenseGroup> expenseGroups = (List<ExpenseGroup>) expenseGroupRepository.findAll();
        return expenseGroups.stream()
                .map(eg -> new ExpenseGroupResponseDto(eg.getId(), eg.getName(), eg.getUser().getId(), eg.getCreated_at(), eg.getUpdated_at()))
                .toList();
    }
}

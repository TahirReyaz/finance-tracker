package com.tahir.finance_manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tahir.finance_manager.entities.ExpenseLimit;

public interface ExpenseLimitRepository extends CrudRepository<ExpenseLimit, Long> {
    Optional<List<ExpenseLimit>> findByUserId(Long userId);
    Optional<ExpenseLimit> findByExpenseType_Id(Long expenseType_Id);
}

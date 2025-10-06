package com.tahir.finance_manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tahir.finance_manager.entities.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
  Optional<Expense> findByItem(String item);
  Optional<List<Expense>> findByUserId(Long userId);
}

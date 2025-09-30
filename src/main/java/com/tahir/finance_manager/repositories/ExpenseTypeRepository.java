package com.tahir.finance_manager.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tahir.finance_manager.entities.ExpenseType;

public interface ExpenseTypeRepository extends CrudRepository<ExpenseType, Long> {
  Optional<ExpenseType> findByName(String name);

  Optional<ExpenseType> findById(Long id);
}

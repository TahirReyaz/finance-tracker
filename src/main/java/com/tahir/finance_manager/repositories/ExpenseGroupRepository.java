package com.tahir.finance_manager.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tahir.finance_manager.entities.ExpenseGroup;

public interface ExpenseGroupRepository extends CrudRepository<ExpenseGroup, Long> {
  Optional<ExpenseGroup> findByName(String anme);
}

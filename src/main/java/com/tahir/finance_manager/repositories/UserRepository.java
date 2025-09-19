package com.tahir.finance_manager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.tahir.finance_manager.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
package com.tahir.finance_manager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tahir.finance_manager.entities.User;
import com.tahir.finance_manager.repositories.UserRepository;

@RestController

@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  private UserRepository userRepo;

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepo.findAll();
  }

}
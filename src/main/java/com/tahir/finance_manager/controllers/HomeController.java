package com.tahir.finance_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @RequestMapping("/")
  public String sayHello() {
    return "Hello";
  }

}
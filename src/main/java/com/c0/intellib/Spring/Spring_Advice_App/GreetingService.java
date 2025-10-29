package com.c0.intellib.Spring.Spring_Advice_App;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

  public String greet(String name) {
    if ("error".equalsIgnoreCase(name)) {
      throw new IllegalArgumentException("Simulated error for name='error'");
    }
    return "Hello, " + (name == null || name.isBlank() ? "World" : name) + "!";
  }
}
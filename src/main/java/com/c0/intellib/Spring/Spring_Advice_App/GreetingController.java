package com.c0.intellib.Spring.Spring_Advice_App;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  private final GreetingService service;

  public GreetingController(GreetingService service) {
    this.service = service;
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(required = false) String name) {
    return service.greet(name);
  }
}

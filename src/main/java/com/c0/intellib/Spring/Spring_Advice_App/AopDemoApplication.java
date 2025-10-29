package com.c0.intellib.Spring.Spring_Advice_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(AopDemoApplication.class, args);
    /*
    curl "http://localhost:8080/hello?name=Dilip"
    curl "http://localhost:8080/hello?name=error"
     */
  }
}

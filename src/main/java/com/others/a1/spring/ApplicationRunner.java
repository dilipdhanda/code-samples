package com.others.a1.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {

    @Autowired
    private AsyncService asyncService;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Starting async tasks...");
        for (int i = 1; i <= 10; i++) {
            asyncService.performTask(i);
        }
        System.out.println("All async tasks triggered.");
    }
}


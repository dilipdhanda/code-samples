package com.c0.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
    To test hit http://localhost:8080/post - it will return response from
    https://jsonplaceholder.typicode.com/posts/1
*/

@SpringBootApplication
public class WebfluxServerApp {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxServerApp.class, args);
    }
}


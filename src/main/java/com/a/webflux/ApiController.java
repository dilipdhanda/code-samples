package com.a.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ApiController {

    private final WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

    /*
    To test hit http://localhost:8080/post - it will return response from
    https://jsonplaceholder.typicode.com/posts/1
     */
    @GetMapping("/post")
    public Mono<String> getPost() {
        return webClient.get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(String.class);
    }
}


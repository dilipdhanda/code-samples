package com.curr.webflux;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public class ReactorExample {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello Mono");
        Flux<String> flux = Flux.just("A", "B", "C");

        mono.subscribe(System.out::println);
        flux.subscribe(item -> System.out.println("Flux item: " + item));
    }
}

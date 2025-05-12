package a.webflux;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientExample {

    public static void main(String[] args) {
        // Disable Walmart VPN, else it cannot reach the website below
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

        Mono<String> responseMono = webClient
                .get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(String.class);

        // Subscribe to non-blocking result
        responseMono.subscribe(
                body -> System.out.println("Response: " + body),
                error -> System.err.println("Error: " + error.getMessage())
        );

        // Only for demonstration, wait a bit to let async call finish
        try { Thread.sleep(2000); } catch (InterruptedException e) {}
    }
}


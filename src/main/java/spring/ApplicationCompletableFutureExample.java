package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ApplicationCompletableFutureExample {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationCompletableFutureExample.class, args);
    }
}

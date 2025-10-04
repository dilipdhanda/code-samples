package com.c1.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/async")
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/run")
    public CompletableFuture<String> runAsyncTasks() throws InterruptedException {
        long start = System.currentTimeMillis();

        CompletableFuture<String> task1 = asyncService.asyncMethodOne();
        CompletableFuture<String> task2 = asyncService.asyncMethodTwo();
        CompletableFuture<String> task3 = asyncService.asyncMethodThree();

        return CompletableFuture.allOf(task1, task2, task3)
                .thenApply(v -> {
                    long end = System.currentTimeMillis();
                    return "All tasks completed in " + (end - start) + " ms.";
                });
    }
}

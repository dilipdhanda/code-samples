package spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Async
    public void performTask(int taskId) {
        System.out.println("Task " + taskId + " started in thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000); // Simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Task " + taskId + " completed in thread: " + Thread.currentThread().getName());
    }
    @Async
    public CompletableFuture<String> asyncMethodOne() throws InterruptedException {
        Thread.sleep(3000);  // Simulate a delay
        String msg = "CompletableFuture - Task 1 Completed!";
        System.out.println(msg);
        return CompletableFuture.completedFuture(msg);
    }
    @Async
    public CompletableFuture<String> asyncMethodTwo() throws InterruptedException {
        Thread.sleep(2000);
        String msg = "CompletableFuture - Task 2 Completed!";
        System.out.println(msg);
        return CompletableFuture.completedFuture(msg);
    }
    @Async
    public CompletableFuture<String> asyncMethodThree() throws InterruptedException {
        Thread.sleep(1000);
        String msg = "CompletableFuture - Task 3 Completed!";
        System.out.println(msg);
        return CompletableFuture.completedFuture(msg);
    }
}

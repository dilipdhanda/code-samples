package com.curr.data_structs.adv;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _CompletableFuture {
    public static void main(String[] args) {
        // Create a CompletableFuture
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running job
            try {
                Thread.sleep(1000);
                int i = 2;
                if (i == 1){
                    throw new IllegalStateException(new InterruptedException());
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });

        // Block and get the result of the Future
        try {
            System.out.println("Completed - " + future.get());  // get is blocking calls, waits until job completed
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error - Future job threw exception: " + e.getMessage());
            // e.printStackTrace();
        }
    }
    public static void main2(String[] args) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Running async task in thread: " + Thread.currentThread().getName());
        });

        // Block and wait for the future to complete
        future.join();
    }
}

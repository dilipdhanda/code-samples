package com.others.a_1_b;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ParallelHttpCalls {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();
        List<URI> uris = Arrays.asList(
                URI.create("https://api.example.com/data1"),
                URI.create("https://api.example.com/data2"),
                URI.create("https://api.example.com/data3")
        );

        ExecutorService executorService = Executors.newFixedThreadPool(3); // Creating a thread pool
        List<Future<HttpResponse<String>>> futures = uris.stream()
                .map(uri -> executorService.submit(() -> fetch(client, uri))) // Submitting tasks
                .collect(Collectors.toList());

        // Processing responses
        for (Future<HttpResponse<String>> future : futures) {
            HttpResponse<String> response = future.get(); // Blocking call
            System.out.println("Response code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        }

        executorService.shutdown();
    }

    private static HttpResponse<String> fetch(HttpClient client, URI uri) {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Request failed: " + uri, e);
        }
    }
}


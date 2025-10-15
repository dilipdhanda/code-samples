package dev.weather.service;

import dev.weather.model.GeoResult;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class GeocodingService {
  private final WebClient client = WebClient.builder()
    .baseUrl("https://nominatim.openstreetmap.org")
    .defaultHeader("User-Agent", "zip-forecast-demo/1.0 (contact: example@example.com)")
    .build();

  public Mono<GeoResult> geocodeToZip(String address) {
    String q = URLEncoder.encode(address, StandardCharsets.UTF_8);
    String path = "/search?format=json&addressdetails=1&limit=1&q=" + q;

    return client.get()
      .uri(path)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(String.class)
      .flatMap(json -> Mono.fromCallable(() -> JsonUtil.parseGeo(json)));
  }
}

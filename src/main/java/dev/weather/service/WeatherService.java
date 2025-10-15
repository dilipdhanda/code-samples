package dev.weather.service;

import dev.weather.model.WeatherResult;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
public class WeatherService {
  private final WebClient client = WebClient.builder()
    .baseUrl("https://api.open-meteo.com")
    .build();

  public Mono<WeatherResult> fetchWeather(String zip, String locationDisplay, double lat, double lon, boolean fromCache) {
    String uri = String.format(
      "/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m" +
        "&daily=temperature_2m_max,temperature_2m_min&timezone=auto",
      lat, lon);

    return client.get()
      .uri(uri)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(String.class)
      .flatMap(json -> Mono.fromCallable(() -> JsonUtil.parseWeather(json, zip, locationDisplay, fromCache, ZonedDateTime.now())));
  }
}

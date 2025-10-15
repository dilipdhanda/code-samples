package dev.weather;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.weather.model.WeatherResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@SpringBootApplication
public class ZipForecastApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZipForecastApplication.class, args);
  }

  @Bean
  public Cache<String, WeatherResult> zipCache() {
    return Caffeine.newBuilder()
      .expireAfterWrite(Duration.ofMinutes(30))
      .maximumSize(2000)
      .build();
  }
}

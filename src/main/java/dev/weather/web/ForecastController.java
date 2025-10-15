package dev.weather.web;

import com.github.benmanes.caffeine.cache.Cache;
import dev.weather.model.GeoResult;
import dev.weather.model.WeatherResult;
import dev.weather.service.GeocodingService;
import dev.weather.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

@Controller
public class ForecastController {

  private final GeocodingService geocoding;
  private final WeatherService weather;
  private final Cache<String, WeatherResult> cache;

  public ForecastController(GeocodingService geocoding, WeatherService weather, Cache<String, WeatherResult> cache) {
    this.geocoding = geocoding;
    this.weather = weather;
    this.cache = cache;
  }

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @PostMapping("/forecast")
  public Mono<String> forecast(@RequestParam("address") String address, Model model) {
    model.addAttribute("address", address);

    return geocoding.geocodeToZip(address)
      .flatMap(geo -> {
        model.addAttribute("resolved", geo.displayName());
        model.addAttribute("zip", geo.zip());

        WeatherResult cached = cache.getIfPresent(geo.zip());
        if (cached != null) {
          // mark as fromCache (copy object)
          WeatherResult shown = new WeatherResult(
            cached.zip(),
            cached.locationDisplay(),
            cached.currentTempC(),
            cached.todayHighC(),
            cached.todayLowC(),
            true,
            cached.fetchedAt()
          );
          model.addAttribute("result", shown);
          return Mono.just("index");
        }

        return weather.fetchWeather(geo.zip(), geo.displayName(), geo.latitude(), geo.longitude(), false)
          .map(fresh -> {
            cache.put(geo.zip(), fresh);
            model.addAttribute("result", fresh);
            return "index";
          });
      })
      .onErrorResume(ex -> {
        model.addAttribute("error", ex.getMessage());
        return Mono.just("index");
      });
  }
}

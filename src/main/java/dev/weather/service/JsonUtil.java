package dev.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.weather.model.GeoResult;
import dev.weather.model.WeatherResult;

import java.time.ZonedDateTime;

class JsonUtil {
  private static final ObjectMapper M = new ObjectMapper();

  static GeoResult parseGeo(String json) throws Exception {
    JsonNode arr = M.readTree(json);
    if (!arr.isArray() || arr.isEmpty()) {
      throw new IllegalArgumentException("Address not found.");
    }
    JsonNode first = arr.get(0);
    String displayName = first.path("display_name").asText();
    String lat = first.path("lat").asText();
    String lon = first.path("lon").asText();

    JsonNode address = first.path("address");
    String zip = address.path("postcode").asText(null);

    if (zip == null || zip.isBlank()) {
      // Fallback: sometimes Nominatim returns "postcode": "" or missing in certain regions
      throw new IllegalArgumentException("Could not determine ZIP code from address.");
    }

    return new GeoResult(displayName, zip, Double.parseDouble(lat), Double.parseDouble(lon));
  }

  static WeatherResult parseWeather(String json,
                                    String zip,
                                    String locationDisplay,
                                    boolean fromCache,
                                    ZonedDateTime fetchedAt) throws Exception {
    JsonNode root = M.readTree(json);

    double current = root.path("current").path("temperature_2m").asDouble(Double.NaN);

    Double high = null, low = null;
    JsonNode daily = root.path("daily");
    if (daily.isObject()) {
      JsonNode highs = daily.path("temperature_2m_max");
      JsonNode lows = daily.path("temperature_2m_min");
      if (highs.isArray() && highs.size() >= 1) high = highs.get(0).asDouble();
      if (lows.isArray() && lows.size() >= 1) low = lows.get(0).asDouble();
    }

    return new WeatherResult(zip, locationDisplay, current, high, low, fromCache, fetchedAt);
  }
}

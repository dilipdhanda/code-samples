package dev.weather.model;

import java.time.ZonedDateTime;

public record WeatherResult(
  String zip,
  String locationDisplay,
  double currentTempC,
  Double todayHighC,
  Double todayLowC,
  boolean fromCache,
  ZonedDateTime fetchedAt
) {}

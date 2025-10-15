package dev.weather.model;

public record GeoResult(
  String displayName,
  String zip,
  double latitude,
  double longitude
) {}


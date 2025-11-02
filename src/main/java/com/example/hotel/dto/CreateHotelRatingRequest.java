package com.example.hotel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore any client-provided id, etc.
public class CreateHotelRatingRequest {
  public String hotelName;
  public String city;
  public Integer score; // boxed for null check
  public LocalDate reviewDate; // optional; defaults to today if null
}

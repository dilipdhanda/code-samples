package com.example.hotel.model;

import java.time.LocalDate;

public class HotelRating {
  private long id;
  private String hotelName;
  private String city;
  private int score; // 0..5 inclusive
  private LocalDate reviewDate; // ISO-8601 (e.g., 2025-11-01)

  public HotelRating() {}

  public HotelRating(long id, String hotelName, String city, int score, LocalDate reviewDate) {
    this.id = id;
    this.hotelName = hotelName;
    this.city = city;
    this.score = score;
    this.reviewDate = reviewDate;
  }

  public long getId() { return id; }
  public void setId(long id) { this.id = id; }
  public String getHotelName() { return hotelName; }
  public void setHotelName(String hotelName) { this.hotelName = hotelName; }
  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }
  public int getScore() { return score; }
  public void setScore(int score) { this.score = score; }
  public LocalDate getReviewDate() { return reviewDate; }
  public void setReviewDate(LocalDate reviewDate) { this.reviewDate = reviewDate; }
}


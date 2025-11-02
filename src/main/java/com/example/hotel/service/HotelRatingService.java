package com.example.hotel.service;

import com.example.hotel.dto.CreateHotelRatingRequest;
import com.example.hotel.dto.UpdateScoreRequest;
import com.example.hotel.model.HotelRating;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class HotelRatingService {
  private final Map<Long, HotelRating> store = new ConcurrentHashMap<>();
  private final AtomicLong idSeq = new AtomicLong(0L);

  public HotelRating add(CreateHotelRatingRequest req) {
    if (req == null) throw badRequest("Request body is required");
    if (isBlank(req.hotelName)) throw badRequest("hotelName is required");
    if (isBlank(req.city)) throw badRequest("city is required");
    if (req.score == null) throw badRequest("score is required");
    validateScore(req.score);
    LocalDate date = (req.reviewDate != null) ? req.reviewDate : LocalDate.now();

    long id = idSeq.incrementAndGet(); // 1,2,3,... sequential
    HotelRating rating = new HotelRating(id, req.hotelName.trim(), req.city.trim(), req.score, date);
    store.put(id, rating);
    return rating;
  }

  public List<HotelRating> top3ByCity(String city) {
    if (isBlank(city)) throw badRequest("city query parameter is required");
    final String key = city.trim();
    return store.values().stream()
      .filter(r -> r.getCity().equalsIgnoreCase(key))
      .sorted(Comparator
        .comparingInt(HotelRating::getScore).reversed()
        .thenComparing(HotelRating::getReviewDate, Comparator.reverseOrder())
        .thenComparing(HotelRating::getHotelName))
      .limit(3)
      .collect(Collectors.toList());
  }

  public HotelRating updateScore(long id, UpdateScoreRequest req) {
    if (req == null || req.score == null) throw badRequest("score is required");
    validateScore(req.score);
    HotelRating rating = store.get(id);
    if (rating == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HotelRating id=" + id + " not found");
    rating.setScore(req.score);
    return rating;
  }

  public Map<String, Object> averageForCity(String city) {
    if (isBlank(city)) throw badRequest("city query parameter is required");
    final String key = city.trim();
    IntSummaryStatistics stats = store.values().stream()
      .filter(r -> r.getCity().equalsIgnoreCase(key))
      .collect(Collectors.summarizingInt(HotelRating::getScore));
    double avg = stats.getCount() == 0 ? 0.0 : stats.getAverage();
    Map<String, Object> resp = new LinkedHashMap<>();
    resp.put("city", key);
    resp.put("average", avg);
    resp.put("count", stats.getCount());
    return resp;
  }

  private static void validateScore(int score) {
    if (score < 0 || score > 5) {
      throw badRequest("score must be between 0 and 5 inclusive");
    }
  }

  private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }

  private static ResponseStatusException badRequest(String msg) {
    return new ResponseStatusException(HttpStatus.BAD_REQUEST, msg);
  }
}
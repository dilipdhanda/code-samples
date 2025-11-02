package com.example.hotel.controller;

import com.example.hotel.dto.CreateHotelRatingRequest;
import com.example.hotel.dto.UpdateScoreRequest;
import com.example.hotel.model.HotelRating;
import com.example.hotel.service.HotelRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;


@RestController
@RequestMapping("/hotels")
public class HotelRatingController {
  private final HotelRatingService service;


  @Autowired
  public HotelRatingController(HotelRatingService service) { this.service = service; }


  /** POST /hotels : Add a new record (server assigns sequential id; ignores client id) */
  @PostMapping
  public ResponseEntity<HotelRating> create(@RequestBody CreateHotelRatingRequest req) {
    HotelRating created = service.add(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }


  /** GET /hotels/top?city=X : Top 3 by score (tie -> most recent review) */
  @GetMapping("/top")
  public List<HotelRating> top3(@RequestParam("city") String city) {
    return service.top3ByCity(city);
  }


  /** PUT /hotels/{id} : Update score */
  @PutMapping("/{id}")
  public HotelRating updateScore(@PathVariable("id") long id, @RequestBody UpdateScoreRequest req) {
    return service.updateScore(id, req);
  }


  /** GET /hotels/average?city=X : Average score for city */
  @GetMapping("/average")
  public Map<String, Object> average(@RequestParam("city") String city) {
    return service.averageForCity(city);
  }


  // Simple error payload for ResponseStatusException
  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Map<String, Object>> handleRSE(ResponseStatusException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("status", ex.getStatusCode().value());
    String error = (ex.getStatusCode() instanceof HttpStatus status)
      ? status.getReasonPhrase()
      : ex.getStatusCode().toString();
    body.put("error", error);
    body.put("message", ex.getReason());
    return ResponseEntity.status(ex.getStatusCode()).body(body);
  }

}

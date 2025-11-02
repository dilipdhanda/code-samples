package com.example.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
curl -X POST http://localhost:8080/hotels \
  -H "Content-Type: application/json" \
  -d '{
    "hotelName": "Seaside Inn",
    "city": "Monterey",
    "score": 5,
    "reviewDate": "2025-11-02"
  }'
curl "http://localhost:8080/hotels/top?city=Monterey"
curl -X PUT http://localhost:8080/hotels/1 \
  -H "Content-Type: application/json" \
  -d '{ "score": 4 }'
curl "http://localhost:8080/hotels/average?city=Monterey"

BAD Request - curl -X POST http://localhost:8080/hotels \
  -H "Content-Type: application/json" \
  -d '{ "hotelName": "Ocean View", "city": "LA" }'
 */

@SpringBootApplication
public class HotelRatingApplication {
  public static void main(String[] args) {
    SpringApplication.run(HotelRatingApplication.class, args);
  }
}

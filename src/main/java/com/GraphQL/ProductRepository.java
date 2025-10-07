package com.GraphQL;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.*;

@Repository
public class ProductRepository {
  private final Map<String, Product> db = new HashMap<>();

  @PostConstruct
  void seed() {
    db.put("p1", new Product("p1","GraphQL Mug","GM-001",12.99,"Ceramic mug",0.3,true,"Kitchen",4.8, Instant.now().toString()));
    db.put("p2", new Product("p2","Reactive T-Shirt","RT-404",19.99,"Cotton tee",0.2,true,"Apparel",4.4, Instant.now().toString()));
  }

  public Optional<Product> findById(String id) { return Optional.ofNullable(db.get(id)); }
  public List<Product> findAll() { return new ArrayList<>(db.values()); }
}


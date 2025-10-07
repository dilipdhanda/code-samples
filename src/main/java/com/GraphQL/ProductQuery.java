package com.GraphQL;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductQuery {

  private final ProductRepository repo;
  public ProductQuery(ProductRepository repo) { this.repo = repo; }

  @QueryMapping
  public Product productById(@Argument String id) {
    return repo.findById(id).orElse(null);
  }

  @QueryMapping
  public List<Product> products() {
    return repo.findAll();
  }
}


package com.GraphQL;

public record Product(
  String id,
  String name,
  String sku,
  Double price,
  String description,
  Double weight,
  Boolean inStock,
  String category,
  Double rating,
  String createdAt
) {}

package com.example.productmanagement.controller.dto;

import com.example.productmanagement.model.entities.Product;

public record ProductDTO(Long id, String name, Double price) {
  public Product toProduct() {
    return new Product(id, name, price);
  }
}

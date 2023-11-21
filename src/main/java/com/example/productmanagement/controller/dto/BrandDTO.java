package com.example.productmanagement.controller.dto;

import com.example.productmanagement.model.entities.Brand;

public record BrandDTO(Long id, String name) {
  public Brand toBrand() {
    return new Brand(id, name);
  }

}

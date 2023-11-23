package com.example.productmanagement.controller.dto;

import com.example.productmanagement.model.entities.Category;

public record CategoryDTO(Long id, String name) {
  public Category toCategory() {
    return new Category(id, name);
  }
}

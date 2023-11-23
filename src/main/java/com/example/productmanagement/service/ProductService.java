package com.example.productmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.productmanagement.model.entities.Product;
import com.example.productmanagement.model.repositories.ProductRepository;

@Service
public class ProductService {

  private ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product insertBrand(Product product) {
    return productRepository.save(product);
  }
}

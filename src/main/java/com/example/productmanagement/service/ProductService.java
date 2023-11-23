package com.example.productmanagement.service;

import com.example.productmanagement.model.entities.Product;
import com.example.productmanagement.model.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product insertProduct(Product product) {
    return productRepository.save(product);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> getProductById(Long id) {
    Optional<Product> productFromDB = productRepository.findById(id);

    if (productFromDB.isPresent()) {
      return Optional.of(productFromDB.get());
    }
    return productFromDB;
  }

  public Optional<Product> updateProduct(Long id, Product product) {
    Optional<Product> productFromDB = productRepository.findById(id);

    if (productFromDB.isPresent()) {
      productFromDB.get().setName(product.getName());
      productFromDB.get().setPrice(product.getPrice());
      return Optional.of(productRepository.save(productFromDB.get()));
    }
    return productFromDB;
  }

  public Optional<Product> deleteProduct(Long id) {
    Optional<Product> productFromDB = productRepository.findById(id);
    
    if (productFromDB.isPresent()) {
      productRepository.deleteById(id);
    }
    
    return productFromDB;
  }
}

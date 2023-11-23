package com.example.productmanagement.controller;

import com.example.productmanagement.controller.dto.ProductDTO;
import com.example.productmanagement.controller.dto.ResponseDTO;
import com.example.productmanagement.model.entities.Product;
import com.example.productmanagement.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ResponseEntity<ResponseDTO<Product>> newProduct(@RequestBody ProductDTO productDTO) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(
            new ResponseDTO<>("Produto cadastrado com sucesso",
                productService.insertProduct(productDTO.toProduct())));
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    if (productService.getAllProducts().size() == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO<Product>> getProduct(@PathVariable Long id) {
    Optional<Product> optionalProduct = productService.getProductById(id);

    if (optionalProduct.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new ResponseDTO<>("Não foi possível localizar um produto com id " + id, null));
    }

    return ResponseEntity
        .ok(new ResponseDTO<>("Produto encontrado!", optionalProduct.get()));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDTO<Product>> updateProduct(@PathVariable Long id,
      @RequestBody ProductDTO productDTO) {
    Optional<Product> optionalProduct = productService.updateProduct(id, productDTO.toProduct());

    if (optionalProduct.isEmpty()) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ResponseDTO<>(
              "Não foi possível atualizar o produto com id "
                  + id
                  + "pois nada foi encontrado no banco de dados",
              null));
    }
    return ResponseEntity
        .ok(new ResponseDTO<>("Produto atualizado com sucesso!", optionalProduct.get()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDTO<Product>> deleteProduct(@PathVariable Long id) {
    Optional<Product> optionalProduct = productService.deleteProduct(id);

    if (optionalProduct.isEmpty()) {
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(new ResponseDTO<>(
              "Não foi possível excluir o produto com id "
                  + id
                  + "pois nada foi encontrado no banco de dados",
              null));
    }
    return ResponseEntity
        .status(HttpStatus.ACCEPTED)
        .body(new ResponseDTO<>("Produto excluído do banco de dados com sucesso!",
            optionalProduct.get()));
  }
}

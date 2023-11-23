package com.example.productmanagement.controller;

import com.example.productmanagement.controller.dto.CategoryDTO;
import com.example.productmanagement.controller.dto.ResponseDTO;
import com.example.productmanagement.model.entities.Category;
import com.example.productmanagement.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @PostMapping
  public ResponseEntity<ResponseDTO<Category>> newCategory(@RequestBody CategoryDTO categoryDTO) {
    Category newCategory = categoryDTO.toCategory();
    Category result = categoryService.insertCategory(newCategory);
    ResponseDTO<Category> responseDTO = new ResponseDTO<Category>("Categoria cadastrada com sucesso!", result);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO<Category>> getCategoryById(@PathVariable Long id) {
    Optional<Category> optionalCategory = categoryService.getCategoryById(id);

    if (optionalCategory.isEmpty()) {
      ResponseDTO<Category> responseDTO = new ResponseDTO<Category>("Nenhum categoria encontrada com o id " + id, null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    Category foundCategory = optionalCategory.get();
    ResponseDTO<Category> responseDTO = new ResponseDTO<Category>("Categoria localizada com sucesso!", foundCategory);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryService.getAllCategories();
    if (categories.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(categories);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDTO<Category>> updateCategory(@PathVariable Long id,
      @RequestBody CategoryDTO categoryDTO) {
    Optional<Category> optionalCategory = categoryService.updateCategory(id, categoryDTO.toCategory());

    if (optionalCategory.isEmpty()) {
      ResponseDTO<Category> responseDTO = new ResponseDTO<Category>("Categoria não encontrada", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Category> responseDTO = new ResponseDTO<Category>("Categoria atualizada com sucesso!",
        optionalCategory.get());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDTO<Category>> deleteCategory(@PathVariable Long id) {
    Optional<Category> optionalCategory = categoryService.deleteCategory(id);

    if (optionalCategory.isEmpty()) {
      ResponseDTO<Category> responseDTO = new ResponseDTO<Category>(
          "Não foi possível excluir pois não há categorias com id " + id, null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Category> responseDTO = new ResponseDTO<Category>("Categoria excluída com sucesso!",
        optionalCategory.get());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
  }
}

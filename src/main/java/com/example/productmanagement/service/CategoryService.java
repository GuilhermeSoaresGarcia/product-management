package com.example.productmanagement.service;

import com.example.productmanagement.model.entities.Category;
import com.example.productmanagement.model.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  private CategoryRepository categoryRepository;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public Category insertCategory(Category category) {
    return categoryRepository.save(category);
  }

  public Optional<Category> getCategoryById(Long id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);

    if (optionalCategory.isPresent()) {
      return Optional.of(optionalCategory.get());
    }
    return optionalCategory;
  }

  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  public Optional<Category> updateCategory(Long id, Category category) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);

    if (optionalCategory.isPresent()) {
      Category categoryFromDB = optionalCategory.get();
      categoryFromDB.setName(category.getName());

      Category updatedCategory = categoryRepository.save(categoryFromDB);
      return Optional.of(updatedCategory);
    }
    return optionalCategory;
  }

  public Optional<Category> deleteCategory(Long id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);

    if (optionalCategory.isPresent()) {
      categoryRepository.deleteById(id);
    }
    return optionalCategory;
  }
}

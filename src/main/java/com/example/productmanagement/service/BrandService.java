package com.example.productmanagement.service;

import com.example.productmanagement.model.entities.Brand;
import com.example.productmanagement.model.repositories.BrandRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {

  private BrandRepository brandRepository;

  @Autowired
  public BrandService(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  public Brand insertBrand(Brand brand) {
    return brandRepository.save(brand);
  }

  public Optional<Brand> getBrandById(Long id) {
    Optional<Brand> optionalBrand = brandRepository.findById(id);

    if (optionalBrand.isPresent()) {
      return Optional.of(optionalBrand.get());
    }
    return optionalBrand;
  }

  public List<Brand> getAllBrands() {
    return brandRepository.findAll();
  }

  public Optional<Brand> updateBrand(Long id, Brand brand) {
    Optional<Brand> optionalBrand = brandRepository.findById(id);

    if (optionalBrand.isPresent()) {
      Brand brandFromDB = optionalBrand.get();
      brandFromDB.setName(brand.getName());

      Brand updatedBrand = brandRepository.save(brandFromDB);
      return Optional.of(updatedBrand);
    }
    return optionalBrand;
  }

  public Optional<Brand> deleteBrand(Long id) {
    Optional<Brand> optionalBrand = brandRepository.findById(id);

    if (optionalBrand.isPresent()) {
      brandRepository.deleteById(id);
    }
    return optionalBrand;
  }

}

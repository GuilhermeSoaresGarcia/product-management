package com.example.productmanagement.model.repositories;

import com.example.productmanagement.model.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}

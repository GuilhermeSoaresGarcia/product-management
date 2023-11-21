package com.example.productmanagement.controller;

import com.example.productmanagement.controller.dto.BrandDTO;
import com.example.productmanagement.controller.dto.ResponseDTO;
import com.example.productmanagement.model.entities.Brand;
import com.example.productmanagement.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandController {

  private BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @PostMapping
  public ResponseEntity<ResponseDTO<Brand>> newBrand(@RequestBody BrandDTO brandDTO) {
    Brand newBrand = brandDTO.toBrand();
    Brand result = brandService.insertBrand(newBrand);
    ResponseDTO<Brand> responseDTO = new ResponseDTO<>("Marca cadastrada com sucesso!", result);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

}

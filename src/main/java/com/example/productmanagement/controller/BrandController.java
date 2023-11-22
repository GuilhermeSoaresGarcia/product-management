package com.example.productmanagement.controller;

import com.example.productmanagement.controller.dto.BrandDTO;
import com.example.productmanagement.controller.dto.ResponseDTO;
import com.example.productmanagement.model.entities.Brand;
import com.example.productmanagement.service.BrandService;
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
    ResponseDTO<Brand> responseDTO = new ResponseDTO<Brand>("Marca cadastrada com sucesso!", result);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDTO<Brand>> getBrandById(@PathVariable Long id) {
    Optional<Brand> optionalBrand = brandService.getBrandById(id);

    if (optionalBrand.isEmpty()) {
      ResponseDTO<Brand> responseDTO = new ResponseDTO<Brand>("Nenhum marca encontrada com o id " + id, null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    Brand foundBrand = optionalBrand.get();
    ResponseDTO<Brand> responseDTO = new ResponseDTO<Brand>("Marca localizada com sucesso!", foundBrand);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping
  public ResponseEntity<List<Brand>> getAllBrands() {
    List<Brand> brands = brandService.getAllBrands();
    if (brands.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(brands);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseDTO<Brand>> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
    Optional<Brand> optionalBrand = brandService.updateBrand(id, brandDTO.toBrand());

    if (optionalBrand.isEmpty()) {
      ResponseDTO<Brand> responseDTO = new ResponseDTO<Brand>("Marca não encontrada", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Brand> responseDTO = new ResponseDTO<Brand>("Marca atualizada com sucesso!", optionalBrand.get());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDTO<Brand>> deleteBrand(@PathVariable Long id) {
    Optional<Brand> optionalBrand = brandService.deleteBrand(id);

    if (optionalBrand.isEmpty()) {
      ResponseDTO<Brand> responseDTO = new ResponseDTO<Brand>(
          "Não foi possível excluir pois não há registros com id " + id, null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Brand> responseDTO = new ResponseDTO<Brand>("Marca excluída com sucesso!", optionalBrand.get());
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
  }
}

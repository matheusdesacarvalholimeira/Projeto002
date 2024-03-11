package com.projeto002.spinrgboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.projeto002.spinrgboot.dtos.ProductRecordDto;
import com.projeto002.spinrgboot.models.productModel;
import com.projeto002.spinrgboot.repository.ProductsRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class ProductsControllers {

    @Autowired
    ProductsRepository productsRepository;
    
    @PostMapping("/products")
    public ResponseEntity<productModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
		var productModel = new productModel();
		BeanUtils.copyProperties(productRecordDto, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(productModel));
	}

  @GetMapping("/products")
  public ResponseEntity<List<productModel>> getAllProducts(){
    return ResponseEntity.status(HttpStatus.OK).body(productsRepository.findAll());
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
    Optional<productModel> product0 = productsRepository.findById(id);
    if(product0.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pasta Nao encontrtada");
    }
    //product0.get().add(linkTo(methodOn(ProductsControllers.class).getAllProducts()).withRel("Lista de Produtos"));
    return ResponseEntity.status(HttpStatus.OK).body(product0.get());
  }

  @PutMapping("products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto){

      Optional<productModel>  product0 = productsRepository.findById(id);
      if (product0.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passta nao encontrada");
      }
      var productModel = product0.get();
      BeanUtils.copyProperties(productRecordDto, productModel);
      return ResponseEntity.status(HttpStatus.OK).body(productsRepository.save(productModel));
    }

    @DeleteMapping
      public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id")UUID id){
        Optional<productModel> product0 = productsRepository.findById(id);
        if (product0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("pasta nao encontrada");
        }
        productsRepository.delete(product0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");
      }
  }


  


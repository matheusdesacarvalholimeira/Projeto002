package com.projeto002.spinrgboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto002.spinrgboot.models.productModel;

import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<productModel, UUID>{
    
}

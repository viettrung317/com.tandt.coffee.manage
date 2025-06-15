package com.tandt.coffee.manage.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tandt.coffee.manage.api.dto.ProductDTO;
import com.tandt.coffee.manage.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	ProductDTO save(ProductDTO productDTO);
}
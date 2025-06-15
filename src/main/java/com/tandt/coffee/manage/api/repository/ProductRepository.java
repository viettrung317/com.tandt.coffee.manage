package com.tandt.coffee.manage.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tandt.coffee.manage.api.dto.ProductDTO;
import com.tandt.coffee.manage.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<List<Product>> findByCustomerNameContainingIgnoreCase(String customerName);

	ProductDTO save(ProductDTO productDTO);
}
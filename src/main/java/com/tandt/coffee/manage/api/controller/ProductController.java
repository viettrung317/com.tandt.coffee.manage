package com.tandt.coffee.manage.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tandt.coffee.manage.api.dto.ProductDTO;
import com.tandt.coffee.manage.api.mappers.ProductMapper;
import com.tandt.coffee.manage.api.payload.ApiResponse;
import com.tandt.coffee.manage.api.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	ProductMapper productMapper;

	@PostMapping
	public ResponseEntity<ApiResponse<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {
		log.info("Received request to create product.");
		ProductDTO createProduct = productService.addProduct(productDTO).orElse(null);
		return ResponseEntity.ok(new ApiResponse<>(true, "Product created successfully", createProduct));
	}
	
	@GetMapping("/id")
	public ResponseEntity<ApiResponse<ProductDTO>> getOrderByID(@PathVariable Long id){
		log.info("Fetching product with id: {}",id);
		ProductDTO productDTO = productService.getProductById(id).orElse(null);
		return ResponseEntity.ok(new ApiResponse<>(true, "Product fetching successfully", productDTO));
	}
	
	@GetMapping("/by-name/{name")
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getsByCustomerName(@PathVariable String customerName){
		log.info("Fetching product with name: {}", customerName);
		List<ProductDTO> productDTOs = productService.getProductsByCustomerName(customerName).orElse(null);
		return ResponseEntity.ok(new ApiResponse<>(true, "Product retrieved", productDTOs));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDTO>> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
		ProductDTO newProductDTO = productService.updateProduct(id, productDTO).orElse(null);
		return ResponseEntity.ok(new ApiResponse<>(true, "Product updated successfully", newProductDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id){
		productService.deleteProduct(id);
		return ResponseEntity.ok(new ApiResponse<>(true, "Product delete successfully", null));
	}
	
}
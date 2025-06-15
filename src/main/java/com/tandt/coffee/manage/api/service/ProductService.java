package com.tandt.coffee.manage.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tandt.coffee.manage.api.dto.ProductDTO;
import com.tandt.coffee.manage.api.exceptions.EntityNotFoundException;
import com.tandt.coffee.manage.api.mappers.ProductMapper;
import com.tandt.coffee.manage.api.model.Product;
import com.tandt.coffee.manage.api.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;

	@Transactional(readOnly = true)
	public Optional<List<ProductDTO>> getAllOrder() {
		log.info("Fetching all products");
		List<ProductDTO> listProductDTOs = productMapper.toListProductDTO(productRepository.findAll());
		return Optional.ofNullable(listProductDTOs);
	}

	@Transactional(readOnly = true)
	public Optional<ProductDTO> getProductById(Long id) {
		ProductDTO productDTO = productMapper.toDTO(productRepository.findById(id).orElse(null));
		return Optional.ofNullable(productDTO);
	}

	public Optional<ProductDTO> addProduct(ProductDTO productDTO) {
		log.info("Adding new product.");

		ProductDTO newProductDTO = productRepository.save(productDTO);
		return Optional.ofNullable(newProductDTO);
	}

	public Optional<ProductDTO> updateProduct(Long id, ProductDTO updateProductDTO) {
		log.info("Updating product with id: {}", id);

		Product updateProduct = productRepository.findById(id).map(existingProduct -> {
			Product mergedProduct = productMapper.toEntity(updateProductDTO);
			return productRepository.save(mergedProduct);
		}).orElseThrow(() -> new EntityNotFoundException(id));
		ProductDTO productDTO = productMapper.toDTO(updateProduct);
		return Optional.ofNullable(productDTO);
	}

	public void deleteProduct(Long id) {
		log.info("Deleting product with ID: {}", id);

		productRepository.findById(id).ifPresentOrElse(product -> {
			productRepository.delete(product);
			log.info("Successfully deleted product with ID: {}", id);
		}, () -> {
			log.warn("Failed to delete - product not found with ID: {}", id);
			throw new EntityNotFoundException(id);
		});
	}
}
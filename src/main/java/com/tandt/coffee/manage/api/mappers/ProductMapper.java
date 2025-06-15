package com.tandt.coffee.manage.api.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.tandt.coffee.manage.api.dto.ProductDTO;
import com.tandt.coffee.manage.api.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	ProductDTO toDTO(Product product);

	@InheritInverseConfiguration(name = "toDTO")
	Product toEntity(ProductDTO productDTO);
	
	List<ProductDTO> toListProductDTO(List<Product> listProducts);
}

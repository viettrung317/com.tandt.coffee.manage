package com.tandt.coffee.manage.api.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tandt.coffee.manage.api.dto.ProductDTO;
import com.tandt.coffee.manage.api.model.Product;

@Mapper(componentModel="Spring")
public interface ProductMapper {
	@Mapping(target = "product", source = "product")
	ProductDTO toDTO(Product product);

	@InheritInverseConfiguration(name = "toDTO")
	@Mapping(target ="product", source= "product")
	Product toEntity(ProductDTO productDTO);
	
	List<ProductDTO> toListProductDTO(List<Product> listProducts);
}

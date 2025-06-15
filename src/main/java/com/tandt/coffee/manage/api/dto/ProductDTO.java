package com.tandt.coffee.manage.api.dto;

import java.io.Serializable;

import com.tandt.coffee.manage.api.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8036517129285850849L;
	private Long id;
    private String name;
    private String description;
    private Double price;
    private int stock;
    private String imagePath;

    public ProductDTO(Product product){
        this.id=product.getId();
        this.name = product.getName();
        this.imagePath= product.getImagePath();
        this.description= product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setImagePath(this.imagePath);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setStock(this.stock);
        return product;
    }
}
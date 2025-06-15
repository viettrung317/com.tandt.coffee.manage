package com.tandt.coffee.manage.api.dto;

import java.io.Serializable;

import com.tandt.coffee.manage.api.model.Order;
import com.tandt.coffee.manage.api.model.OrderDetail;
import com.tandt.coffee.manage.api.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3356044431911547543L;
	private Long id;
	private Long orderId;
	private Product  product;
	private Integer quantily;
	private Double price;
	
	public OrderDetailDTO(OrderDetail orderDetail) {
		this.id = orderDetail.getId();
		this.orderId = orderDetail.getOrder().getId();
		this.product = orderDetail.getProduct();
		this.quantily = orderDetail.getQuantity();
		this.price = orderDetail.getPrice();
	}
	
	public OrderDetail getOrderDetail(Order order) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(this.id);
		orderDetail.setOrder(order);
		orderDetail.setProduct(this.product);
		orderDetail.setQuantity(this.quantily);
		orderDetail.setPrice(this.price);
		return orderDetail;
		}

}

package com.tandt.coffee.manage.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tandt.coffee.manage.api.dto.OrderDTO;
import com.tandt.coffee.manage.api.mappers.OrderMapper;
import com.tandt.coffee.manage.api.payload.ApiResponse;
import com.tandt.coffee.manage.api.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
	
	@Autowired OrderService orderService;
	@Autowired OrderMapper orderMapper;
	
	@PostMapping
	public ResponseEntity<ApiResponse<OrderDTO>> createOrder(@RequestBody OrderDTO orderDTO){
		log.info("Received request to create order.");
		OrderDTO createOrder = orderService.addOrder(orderDTO).orElse(null);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(true, "Order create successfully", createOrder));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<OrderDTO>> getOrderByid(@PathVariable Long id){
		log.info("Fetching order with ID: {}", id);
		OrderDTO orderDto = orderService.getOrderById(id).orElse(new OrderDTO());
		return ResponseEntity.ok(new ApiResponse<>(true, "Order fetching successfully", orderDto));
		
	}
	
	@GetMapping("/by-name/{name}")
	public ResponseEntity<ApiResponse<List<OrderDTO>>> getsByCustomerName(@PathVariable String name) {
		log.info("Fetching order with name: {}", name);
		List<OrderDTO> orderDTOs = orderService.getOrdersByCustomerName(name).orElse(new ArrayList<OrderDTO>());
		return ResponseEntity.ok(new ApiResponse<>(true, "Order retrieved", orderDTOs));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders(){
		log.info("Fetching all orders");
		List<OrderDTO> listOrderDTOs = orderService.getAllOrder().orElse(new ArrayList<OrderDTO>());
		return ResponseEntity.ok(new ApiResponse<>(true, "Order retrieved", listOrderDTOs));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<OrderDTO>> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO){
		log.info("Updating order with Id: {}", id);
		OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO).orElse(new OrderDTO());
		return ResponseEntity.ok(new ApiResponse<>(true, "Order updated successfully", updatedOrder));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Long id){
		log.info("Deleting order with Id: {}", id);
		orderService.deleteOrder(id);
		return ResponseEntity.ok(new ApiResponse<>(true, "Order deleted successfully", null));
	}
}
package com.tandt.coffee.manage.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tandt.coffee.manage.api.dto.OrderDTO;
import com.tandt.coffee.manage.api.exceptions.EntityNotFoundException;
import com.tandt.coffee.manage.api.mappers.OrderMapper;
import com.tandt.coffee.manage.api.model.Order;
import com.tandt.coffee.manage.api.model.OrderDetail;
import com.tandt.coffee.manage.api.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {

	@Autowired
	private final OrderRepository orderRepository;

	@Autowired
	private final OrderMapper orderMapper;

	@Transactional(readOnly = true)
	public Optional<List<OrderDTO>> getAllOrder() {
		List<OrderDTO> orderDTOs = orderMapper.toDTOList(orderRepository.findAll());
		return Optional.ofNullable(orderDTOs);
	}

	@Transactional(readOnly = true)
	public Optional<OrderDTO> getOrderById(Long orderId) {
		log.info("Fetching order with ID: {}", orderId);
		Order order = orderRepository.findById(orderId).orElse(null);
		return Optional.ofNullable(orderMapper.toDTO(order));
	}

	@Transactional(readOnly = true)
	public Optional<List<OrderDTO>> getOrdersByCustomerName(String customerName) {
		Optional<List<Order>> orderOptionals = orderRepository.findByCustomerNameContainingIgnoreCase(customerName);

		List<Order> orders = orderOptionals.orElse(new ArrayList<Order>());
		if (orders.isEmpty()) {
			return Optional.empty();
		}

		List<OrderDTO> dtos = orders.stream().map(orderMapper::toDTO).collect(Collectors.toList());

		return Optional.ofNullable(dtos);
	}
	
	public Optional<OrderDTO> addOrder(OrderDTO orderDTO) {
		log.info("Adding new order.");

		OrderDTO savedOrder = orderRepository.save(orderDTO);
		return Optional.ofNullable(savedOrder);
	}

	public Optional<OrderDTO> updateOrder(Long orderId, OrderDTO orderDTO) {
	    log.info("Updating order with ID: {}", orderId);

	    Order updatedOrder = orderRepository.findById(orderId)
	        .map(existingOrder -> {
	            Order mergedOrder = toEntityWithParent(existingOrder, orderDTO);
	            return orderRepository.save(mergedOrder);
	        })
	        .orElseThrow(() -> new EntityNotFoundException(orderId));

	    log.info("Order updated successfully with ID: {}", updatedOrder.getId());
	    return Optional.of(orderMapper.toDTO(updatedOrder));
	}

 
	private Order toEntityWithParent(Order existingOrder, OrderDTO orderDTO) {
		Order order = orderMapper.toEntity(orderDTO);

		existingOrder.getOrderDetails().clear();

		if (orderDTO.getOrderDetailList() != null) {
			List<OrderDetail> newOrderDetails = orderDTO.getOrderDetailList().stream().map(detailDTO -> {
				OrderDetail detail = orderMapper.toOrderDetailEntity(detailDTO);
				detail.setOrder(existingOrder);
				return detail;
			}).collect(Collectors.toList());

			existingOrder.getOrderDetails().addAll(newOrderDetails);
		}
		return order;
	}

	public void deleteOrder(Long orderId) {
		log.info("Deleting order with ID: {}", orderId);

		orderRepository.findById(orderId).ifPresentOrElse(order -> {
			orderRepository.delete(order);
			log.info("Successfully deleted order with ID: {}", orderId);
		}, () -> {
			log.warn("Failed to delete - order not found with ID: {}", orderId);
			throw new EntityNotFoundException(orderId);
		});
	}

}
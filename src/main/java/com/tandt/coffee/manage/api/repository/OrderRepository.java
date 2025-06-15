package com.tandt.coffee.manage.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tandt.coffee.manage.api.dto.OrderDTO;
import com.tandt.coffee.manage.api.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<List<Order>> findByCustomerNameContainingIgnoreCase(String customerName);

	OrderDTO save(OrderDTO orderDTO);

}
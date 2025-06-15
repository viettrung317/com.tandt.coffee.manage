package com.tandt.coffee.manage.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tandt.coffee.manage.api.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}

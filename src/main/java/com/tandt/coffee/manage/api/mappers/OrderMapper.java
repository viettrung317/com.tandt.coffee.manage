package com.tandt.coffee.manage.api.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tandt.coffee.manage.api.dto.OrderDTO;
import com.tandt.coffee.manage.api.dto.OrderDetailDTO;
import com.tandt.coffee.manage.api.model.Order;
import com.tandt.coffee.manage.api.model.OrderDetail;

@Mapper(componentModel ="Spring")
public interface OrderMapper {
	@Mapping(target = "orderDetails", source = "orderDetails")
	OrderDTO toDTO(Order order);
	
	@InheritInverseConfiguration(name = "toDTO")
	@Mapping(target = "orderDetails", ignore = true)
	Order toEntity(OrderDTO orderDTO);
	
	List<OrderDTO> toDTOList(List<Order> orders);
	
	OrderDetailDTO toOrderDetailDTO(OrderDetail orderDetail);
	
	@InheritInverseConfiguration(name = "toOrderDetailDTO")
    OrderDetail toOrderDetailEntity(OrderDetailDTO orderDetailDTO);
	
	// Optional: Custom method for handling orderDetails collection
    default List<OrderDetailDTO> mapOrderDetails(List<OrderDetail> orderDetails) {
        if (orderDetails == null) {
            return Collections.emptyList();
        }
        return orderDetails.stream()
                .map(this::toOrderDetailDTO)
                .collect(Collectors.toList());
    }
}

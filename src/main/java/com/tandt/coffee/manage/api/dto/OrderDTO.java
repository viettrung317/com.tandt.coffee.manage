package com.tandt.coffee.manage.api.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6558350849438304156L;
	private Long id;
    private Long userId;
    private Long customerId;
    private List<OrderDetailDTO> orderDetailList;
    private Date orderDate;
    private Double totalAmount;
}
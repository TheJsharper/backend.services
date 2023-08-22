package com.jsharper.orderservice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Getter
@Setter
public class PurchaseOrderResponseDto {
	private Integer orderId;
	private Integer userId;
	private String productId;
	private Integer amount;
	private OrderStatus status;
}

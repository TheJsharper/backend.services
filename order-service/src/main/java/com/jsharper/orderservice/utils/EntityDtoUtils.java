package com.jsharper.orderservice.utils;

import org.springframework.beans.BeanUtils;

import com.jsharper.orderservice.dtos.OrderStatus;
import com.jsharper.orderservice.dtos.PurchaseOrderRequestDto;
import com.jsharper.orderservice.dtos.PurchaseOrderResponseDto;
import com.jsharper.orderservice.dtos.RequestContext;
import com.jsharper.orderservice.dtos.TransactionRequestDto;
import com.jsharper.orderservice.dtos.TransactionStatus;
import com.jsharper.orderservice.entities.PurchaseOrder;

public class EntityDtoUtils {

	public static PurchaseOrderResponseDto getPurchaseOrderResponseDto(PurchaseOrder purchaseOrder) {
		PurchaseOrderResponseDto dto = new PurchaseOrderResponseDto();
		BeanUtils.copyProperties(purchaseOrder, dto);
		dto.setUserId(purchaseOrder.getId());
		return dto;
	}

	public static void setTransactionRequestDto(RequestContext requestContext) {
		TransactionRequestDto requestDto = new TransactionRequestDto();
		requestDto.setUserId(requestContext.getPurchaseOrderRequestDto().getUserId());
		requestDto.setAmount(requestContext.getProductDto().getPrice());
		requestContext.setTransactionRequestDto(requestDto);

	}

	public static PurchaseOrder getPurchaseOrder(RequestContext rc) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		PurchaseOrderRequestDto dto = rc.getPurchaseOrderRequestDto();
		purchaseOrder.setUserId(dto.getUserId());
		purchaseOrder.setProductId(dto.getProductId());
		purchaseOrder.setAmount(rc.getProductDto().getPrice());
		TransactionStatus status = rc.getTransactionResponseDto().getStatus();
		OrderStatus orderStatus = TransactionStatus.APROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;
		purchaseOrder.setStatus(orderStatus);
		return purchaseOrder;

	}
}

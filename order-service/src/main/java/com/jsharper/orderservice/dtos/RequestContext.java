package com.jsharper.orderservice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Getter
@Setter
public class RequestContext {

	private PurchaseOrderRequestDto purchaseOrderRequestDto;
	private ProductDto productDto;
	private TransactionRequestDto transactionRequestDto;
	private TransactionResponseDto transactionResponseDto;

	public RequestContext(PurchaseOrderRequestDto purchaseOrderRequestDto) {

		this.purchaseOrderRequestDto = purchaseOrderRequestDto;
	}

}

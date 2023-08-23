package com.jsharper.orderservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsharper.orderservice.clients.ProductClient;
import com.jsharper.orderservice.clients.UserClient;
import com.jsharper.orderservice.dtos.PurchaseOrderRequestDto;
import com.jsharper.orderservice.dtos.PurchaseOrderResponseDto;
import com.jsharper.orderservice.dtos.RequestContext;
import com.jsharper.orderservice.repositories.PurchaseOrderRepository;
import com.jsharper.orderservice.utils.EntityDtoUtils;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderFullfillmentService {
	@Autowired
	private ProductClient productClient;

	@Autowired
	private UserClient userClient;

	@Autowired
	private PurchaseOrderRepository orderRepository;

	public Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> requeestDtono) {
		return requeestDtono.map(RequestContext::new).flatMap(this::productRequestResponse)
				.doOnNext(EntityDtoUtils::setTransactionRequestDto).flatMap(this::userRequestResponse)
				.map(EntityDtoUtils::getPurchaseOrder).map(this.orderRepository::save)
				.map(EntityDtoUtils::getPurchaseOrderResponseDto).subscribeOn(Schedulers.boundedElastic());
	}

	private Mono<RequestContext> productRequestResponse(RequestContext rc) {
		return this.productClient.getProductById(rc.getPurchaseOrderRequestDto().getProductId())
				.doOnNext(rc::setProductDto).thenReturn(rc);
	}

	private Mono<RequestContext> userRequestResponse(RequestContext rc) {
		return this.userClient.authorizeTransaction(rc.getTransactionRequestDto())
				.doOnNext(rc::setTransactionResponseDto).thenReturn(rc);
	}
}

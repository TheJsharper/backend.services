package com.jsharper.orderservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsharper.orderservice.dtos.PurchaseOrderResponseDto;
import com.jsharper.orderservice.entities.PurchaseOrder;
import com.jsharper.orderservice.repositories.PurchaseOrderRepository;
import com.jsharper.orderservice.utils.EntityDtoUtils;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class OrderQueryService {

	@Autowired
	private PurchaseOrderRepository orderRepository;
	
	
	
	
	public Flux<PurchaseOrderResponseDto> getProductByUserId(int userId) {
		List<PurchaseOrder> purchaseOrders = this.orderRepository.findByUserId(userId);

		return Flux.fromIterable(purchaseOrders).map(EntityDtoUtils::getPurchaseOrderResponseDto)
				.subscribeOn(Schedulers.boundedElastic());
	}

}

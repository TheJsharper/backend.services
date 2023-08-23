package com.jsharper.orderservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsharper.orderservice.dtos.PurchaseOrderRequestDto;
import com.jsharper.orderservice.dtos.PurchaseOrderResponseDto;
import com.jsharper.orderservice.services.OrderFullfillmentService;
import com.jsharper.orderservice.services.OrderQueryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {

	@Autowired
	private OrderFullfillmentService service;

	@Autowired
	private OrderQueryService queryService;

	@PostMapping
	public Mono<PurchaseOrderResponseDto> order(@RequestBody Mono<PurchaseOrderRequestDto> requestDto) {

		return this.service.processOrder(requestDto);
	}

	@GetMapping("user/{userId}")
	public Flux<PurchaseOrderResponseDto> getOrderByUserId(@PathVariable int id) {
		return this.queryService.getProductByUserId(id);

	}
}

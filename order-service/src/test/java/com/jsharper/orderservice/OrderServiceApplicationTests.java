package com.jsharper.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jsharper.orderservice.clients.ProductClient;
import com.jsharper.orderservice.clients.UserClient;
import com.jsharper.orderservice.dtos.ProductDto;
import com.jsharper.orderservice.dtos.PurchaseOrderRequestDto;
import com.jsharper.orderservice.dtos.PurchaseOrderResponseDto;
import com.jsharper.orderservice.dtos.UserDto;
import com.jsharper.orderservice.services.OrderFullfillmentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private UserClient userClient;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private OrderFullfillmentService fullfillmentService;

	@Test
	void contextLoads() {
		Flux<PurchaseOrderResponseDto> dtoFlux = Flux
				.zip(this.userClient.getAllUsers(), this.productClient.getAllProducts())
				.map(t -> buildDto(t.getT1(), t.getT2()))
				.flatMap(dto -> this.fullfillmentService.processOrder(Mono.just(dto))).doOnNext(System.out::println);

		StepVerifier.create(dtoFlux).expectNextCount(4).verifyComplete();
	}

	private PurchaseOrderRequestDto buildDto(UserDto userDto, ProductDto productDto) {
		PurchaseOrderRequestDto dto = new PurchaseOrderRequestDto();
		dto.setUserId(userDto.getId());
		dto.setProductId(productDto.getId());
		return dto;
	}

}

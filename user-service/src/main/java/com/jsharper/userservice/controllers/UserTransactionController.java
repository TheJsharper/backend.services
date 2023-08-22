package com.jsharper.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsharper.userservice.dtos.TransactionRequestDto;
import com.jsharper.userservice.dtos.TransactionResponseDto;
import com.jsharper.userservice.entities.UserTransaction;
import com.jsharper.userservice.services.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user/transaction")
public class UserTransactionController {

	@Autowired
	private TransactionService service;

	@PostMapping
	public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> requestDto) {
		return requestDto.flatMap(this.service::createTransaction);
	}

	@GetMapping
	public Flux<UserTransaction> getByUserId(@RequestParam("userId") int userId) {
		return this.service.getByUserId(userId);
	}
}

package com.jsharper.orderservice.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.jsharper.orderservice.dtos.TransactionRequestDto;
import com.jsharper.orderservice.dtos.TransactionResponseDto;
import com.jsharper.orderservice.dtos.UserDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class UserClient {
	private final WebClient webClient;

	public UserClient(@Value("${user.service.url}") String url) {
		this.webClient = WebClient.builder().baseUrl(url).build();
	}

	public Mono<TransactionResponseDto> authorizeTransaction(TransactionRequestDto requestDto) {

		return this.webClient.post().uri("transaction").bodyValue(requestDto).retrieve()
				.bodyToMono(TransactionResponseDto.class);
	}
	public Flux<UserDto> getAllUsers() {
		return this.webClient.get().uri("all").retrieve().bodyToFlux(UserDto.class);
	}

}

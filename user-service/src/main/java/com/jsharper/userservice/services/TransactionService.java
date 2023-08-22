package com.jsharper.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsharper.userservice.dtos.TransactionRequestDto;
import com.jsharper.userservice.dtos.TransactionResponseDto;
import com.jsharper.userservice.dtos.TransactionStatus;
import com.jsharper.userservice.entities.UserTransaction;
import com.jsharper.userservice.repositories.UserRepository;
import com.jsharper.userservice.repositories.UserTransactionRepository;
import com.jsharper.userservice.utils.EntityDtoUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserTransactionRepository userTransactionRepository;

	public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto) {

		return this.userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
				.filter(Boolean::booleanValue).map(b -> EntityDtoUtil.toEntity(requestDto))
				.flatMap(this.userTransactionRepository::save)
				.map(ut -> EntityDtoUtil.toDto(requestDto, TransactionStatus.APROVED))
				.defaultIfEmpty(EntityDtoUtil.toDto(requestDto, TransactionStatus.DECLINED));
	}
	
	public Flux<UserTransaction> getByUserId(int userId){
		return this.userTransactionRepository.findByUserId(userId);
	}

}

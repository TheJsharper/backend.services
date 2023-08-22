package com.jsharper.userservice.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.jsharper.userservice.entities.UserTransaction;

import reactor.core.publisher.Flux;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {

	Flux<UserTransaction> findByUserId(int userId);
}

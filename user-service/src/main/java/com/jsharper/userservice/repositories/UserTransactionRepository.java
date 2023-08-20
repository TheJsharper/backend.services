package com.jsharper.userservice.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.jsharper.userservice.entities.UserTransaction;

public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction, Integer> {

}

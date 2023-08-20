package com.jsharper.userservice.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.jsharper.userservice.entities.User;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

}

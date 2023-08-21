package com.jsharper.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsharper.userservice.dtos.UserDto;
import com.jsharper.userservice.services.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("all")
	public Flux<UserDto> getAll() {

		return service.all();
	}

	@GetMapping("{id}")
	public Mono<ResponseEntity<UserDto>> getBy(@PathVariable Integer id) {
		return this.service.getById(id).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build())
				.flatMap(e -> Mono.just(e));

	}

	@PostMapping
	public Mono<UserDto> insert(@RequestBody Mono<UserDto> userDto) {
		return this.service.insert(userDto);
	}

	@PutMapping("{id}")
	public Mono<ResponseEntity<UserDto>> update(@PathVariable int id, @RequestBody Mono<UserDto> userDto) {

		return this.service.update(id, userDto).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public Mono<Void> delete(@PathVariable("id") Integer id) {
		return service.delete(id).defaultIfEmpty(null);
	}

}

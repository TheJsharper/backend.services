package com.jsharper.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsharper.userservice.dtos.UserDto;
import com.jsharper.userservice.entities.User;
import com.jsharper.userservice.repositories.UserRepository;
import com.jsharper.userservice.utils.EntityDtoUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Flux<UserDto> all() {
		return this.userRepository.findAll().map(e -> EntityDtoUtil.toDto(e, UserDto.class));
	}

	public Mono<UserDto> getById(final Integer id) {
		return this.userRepository.findById(id).map(e -> EntityDtoUtil.toDto(e, UserDto.class));
	}

	public Mono<UserDto> insert(Mono<UserDto> userDto) {
		return userDto.map(e -> EntityDtoUtil.toEntity(e, User.class)).flatMap(this.userRepository::save)
				.map(e -> EntityDtoUtil.toDto(e, UserDto.class));
	}

	public Mono<UserDto> update(Integer id, Mono<UserDto> userDto) {

		Mono<User> foundById = this.userRepository.findById(id);

		Mono<User> toEntity = foundById.flatMap(e -> userDto.map(dto -> EntityDtoUtil.toEntity(dto, User.class)));

		Mono<User> saved = toEntity.doOnNext(e -> e.setId(id)).flatMap(this.userRepository::save);

		Mono<UserDto> valueSaved = saved.map(dto -> EntityDtoUtil.toDto(dto, UserDto.class));

		return valueSaved;
	}

	public Mono<Void> delete(final Integer id) {
		return this.userRepository.deleteById(id);
	}

}

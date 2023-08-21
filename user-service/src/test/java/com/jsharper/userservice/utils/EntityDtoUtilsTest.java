package com.jsharper.userservice.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jsharper.userservice.dtos.UserDto;
import com.jsharper.userservice.entities.User;

public class EntityDtoUtilsTest {

	@Test
	public void entityToDtoTest() {

		User user = new User();

		user.setName("Test");

		user.setId(888);

		user.setBalance(52);

		UserDto result = EntityDtoUtil.toDo(user, UserDto.class);

		Assertions.assertEquals(user.getBalance(), result.getBalance());

		System.out.println("==>" + user.getClass().getName() + " -->" + user);

		System.out.println("==>" + result.getClass().getName() + " -->" + result);

	}

	@Test
	public void entityDtoToEntityTest() {

		UserDto userDto = new UserDto();

		userDto.setName("Test");

		userDto.setId(888);

		userDto.setBalance(52);

		User result = EntityDtoUtil.toDo(userDto, User.class);

		Assertions.assertEquals(userDto.getBalance(), result.getBalance());

		System.out.println("==>" + userDto.getClass().getName() + " -->" + userDto);

		System.out.println("==>" + result.getClass().getName() + " -->" + result);

	}
}

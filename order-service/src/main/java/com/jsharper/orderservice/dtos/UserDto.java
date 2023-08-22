package com.jsharper.orderservice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class UserDto {

	private Integer id;
	private String name;
	private Integer balance;
}

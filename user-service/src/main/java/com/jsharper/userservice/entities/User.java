package com.jsharper.userservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@Table("USERS")
public class User {
	@Id
	private Integer id;
	private String name;
	private Integer balance;
}

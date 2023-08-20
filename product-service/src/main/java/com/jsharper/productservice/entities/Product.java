package com.jsharper.productservice.entities;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class Product {

	@Id
	private String id;
	private String description;
	private Integer price;
}

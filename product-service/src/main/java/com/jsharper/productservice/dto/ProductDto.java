package com.jsharper.productservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class ProductDto {
	
	private String id;
	private String description;
	private Integer price;

}

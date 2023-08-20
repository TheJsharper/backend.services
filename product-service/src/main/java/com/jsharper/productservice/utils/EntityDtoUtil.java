package com.jsharper.productservice.utils;

import org.springframework.beans.BeanUtils;

import com.jsharper.productservice.dto.ProductDto;
import com.jsharper.productservice.entities.Product;

public class EntityDtoUtil {

	public static ProductDto toDto(Product product) {

		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(product, dto);

		return dto;
	}

	public static Product toEntity(ProductDto productDto) {

		Product product = new Product();
		BeanUtils.copyProperties(productDto, product);

		return product;
	}
}

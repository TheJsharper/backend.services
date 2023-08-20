package com.jsharper.productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.jsharper.productservice.dto.ProductDto;
import com.jsharper.productservice.repositories.ProductRepository;
import com.jsharper.productservice.utils.EntityDtoUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Flux<ProductDto> getAll() {

		return this.productRepository.findAll().map(EntityDtoUtil::toDto);
	}

	public Flux<ProductDto> getByPriceRange(int min, int max) {

		return this.productRepository.findByPriceBetween(Range.closed(min, max)).map(EntityDtoUtil::toDto);
	}

	public Mono<ProductDto> getBy(String id) {
		return this.productRepository.findById(id).map(EntityDtoUtil::toDto);
	}

	public Mono<ProductDto> insert(Mono<ProductDto> productDto) {
		return productDto.map(EntityDtoUtil::toEntity).flatMap(this.productRepository::insert)
				.doOnNext((e)->{
					
					System.out.println("===>x"+e);
				}).map((e) -> EntityDtoUtil.toDto(e));
	}

	public Mono<ProductDto> update(String id, Mono<ProductDto> productMono) {
		return this.productRepository.findById(id)
				.flatMap(p -> productMono.map(EntityDtoUtil::toEntity).doOnNext(e -> e.setId(id)))
				.flatMap(this.productRepository::save).map(EntityDtoUtil::toDto);
	}

	public Mono<Void> delete(String id) {
		return this.productRepository.deleteById(id);
	}

}

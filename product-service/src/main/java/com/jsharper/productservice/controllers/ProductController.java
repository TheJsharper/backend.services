package com.jsharper.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsharper.productservice.dto.ProductDto;
import com.jsharper.productservice.services.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("all")
	public Flux<ProductDto> all() {

		return this.service.getAll();
	}

	@GetMapping("price-range")
	public Flux<ProductDto> getByPriceRange(@RequestParam("min") int min, @RequestParam("max") int max) {

		return this.service.getByPriceRange(min, max);
	}

	@GetMapping("{id}")
	public Mono<ResponseEntity<ProductDto>> getById(@PathVariable String id) {
		return this.service.getBy(id).map(ResponseEntity::ok)

				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ProductDto> insert(@RequestBody Mono<ProductDto> productMono) {
		return service.insert(productMono);
	}

	@PutMapping("{id}")
	public Mono<ResponseEntity<ProductDto>> update(@PathVariable String id, @RequestBody Mono<ProductDto> productMono) {
		return this.service.update(id, productMono).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return this.service.delete(id);//.map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
	}
}

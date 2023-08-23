package com.jsharper.orderservice.entities;

import jakarta.persistence.Id;

import com.jsharper.orderservice.dtos.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@Entity
public class PurchaseOrder {

	@Id
	@GeneratedValue
	private Integer id;
	private String productId;
	private Integer userId;
	private Integer amount;
	private OrderStatus status;
}

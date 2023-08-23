package com.jsharper.orderservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsharper.orderservice.entities.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

	List<PurchaseOrder> findByUserId(int userId);
}

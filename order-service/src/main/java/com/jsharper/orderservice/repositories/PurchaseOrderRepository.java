package com.jsharper.orderservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsharper.orderservice.entities.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}

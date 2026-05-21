package com.project.eduardo.repositories;

import com.project.eduardo.entities.OrderItem;
import com.project.eduardo.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}

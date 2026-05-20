package com.project.webserviceproject.repositories;

import com.project.webserviceproject.entities.Category;
import com.project.webserviceproject.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}

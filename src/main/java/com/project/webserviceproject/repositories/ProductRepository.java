package com.project.webserviceproject.repositories;

import com.project.webserviceproject.entities.Order;
import com.project.webserviceproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

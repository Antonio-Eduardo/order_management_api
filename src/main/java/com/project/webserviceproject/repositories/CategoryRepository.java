package com.project.webserviceproject.repositories;

import com.project.webserviceproject.entities.Category;
import com.project.webserviceproject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

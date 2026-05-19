package com.project.webserviceproject.repositories;

import com.project.webserviceproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}

package com.project.webserviceproject.services;

import com.project.webserviceproject.entities.User;
import com.project.webserviceproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }
}

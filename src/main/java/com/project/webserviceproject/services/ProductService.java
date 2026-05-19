package com.project.webserviceproject.services;

import com.project.webserviceproject.entities.Product;
import com.project.webserviceproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product FindById(Long id){
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }
}

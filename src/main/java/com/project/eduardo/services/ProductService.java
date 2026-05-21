package com.project.eduardo.services;

import com.project.eduardo.entities.Category;
import com.project.eduardo.entities.Product;
import com.project.eduardo.repositories.CategoryRepository;
import com.project.eduardo.repositories.ProductRepository;
import com.project.eduardo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product FindById(Long id){
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }

    public Product inserProduct(Product obj){
        Set<Category> category = obj.getCategories().stream()
                .map(cat -> categoryRepository
                .findById(cat.getId())
                .orElseThrow(() -> new ResourceNotFoundException(cat.getId())))
                .collect(Collectors.toSet());

        obj.setCategories(category);

       return repository.save(obj);
    }
}

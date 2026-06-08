package com.project.eduardo.services;

import com.project.eduardo.dto.response.CategoryDTOresponse;
import com.project.eduardo.dto.response.ProductDTOresponse;
import com.project.eduardo.entities.Category;
import com.project.eduardo.repositories.CategoryRepository;
import com.project.eduardo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public CategoryDTOresponse FindById(Long id) {
        Category obj = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Categoria não encontrada"
                        + id));
        Set<ProductDTOresponse> products =
                obj.getProducts().stream()
                        .map(product -> {
                            ProductDTOresponse dto = new ProductDTOresponse();
                                    dto.setId(product.getId());
                                    dto.setName(product.getName());
                                    dto.setDescription(product.getDescription());
                                    dto.setPrice(product.getPrice());
                                    dto.setImgUrl(product.getImgUrl());
                                    return dto;
                        }).collect(Collectors.toSet());
        CategoryDTOresponse response = new CategoryDTOresponse();
        response.setId(obj.getId());
        response.setName(obj.getName());
        response.setProducts(products);

        return response;
    }

    public Category insertCategory(Category obj) {
        return repository.save(obj);
    }
}

package com.project.eduardo.services;

import com.project.eduardo.dto.request.OrderItemDTOrequest;
import com.project.eduardo.dto.request.ProductDTOrequest;
import com.project.eduardo.dto.response.CategoryDTOresponse;
import com.project.eduardo.dto.response.OrderItemDTOresponse;
import com.project.eduardo.dto.response.ProductDTOresponse;
import com.project.eduardo.entities.Category;
import com.project.eduardo.entities.Product;
import com.project.eduardo.repositories.CategoryRepository;
import com.project.eduardo.repositories.ProductRepository;
import com.project.eduardo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public ProductDTOresponse FindById(Long id){
        Product produto = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Produto não encontrado! ID:" + id));
        ProductDTOresponse response = new ProductDTOresponse();
        response.setId(produto.getId());
        response.setName(produto.getName());
        response.setPrice(produto.getPrice());
        response.setDescription(produto.getDescription());
        response.setImgUrl(produto.getImgUrl());

        response.setCategories(produto.getCategories().stream().map(category -> {
            CategoryDTOresponse categoryDTOresponse = new CategoryDTOresponse();
            categoryDTOresponse.setId(category.getId());
            categoryDTOresponse.setName(category.getName());
            return categoryDTOresponse;

        }).collect(Collectors.toSet()));
        return response;
    }

    @Transactional
    public ProductDTOresponse inserProduct(ProductDTOrequest obj){
        Product product = new Product();
        product.setName(obj.getName());
        product.setPrice(obj.getPrice());
        product.setDescription(obj.getDescription());
        product.setImgUrl(obj.getImgUrl());
        product.setCategories(obj.getCategories().stream().map(categoryDTOrequest -> {
            Category category = new Category();
            category.setName(categoryDTOrequest.getName());
            return categoryRepository.save(category);
        }).collect(Collectors.toSet()));

        Product savedProduct = repository.save(product);
        ProductDTOresponse response = new ProductDTOresponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setPrice(savedProduct.getPrice());
        response.setDescription(savedProduct.getDescription());
        response.setImgUrl(savedProduct.getImgUrl());
        response.setCategories(savedProduct.getCategories().stream().map(category -> {
            CategoryDTOresponse dt0response = new CategoryDTOresponse();
            dt0response.setName(category.getName());
            dt0response.setId(category.getId());
            return dt0response;
        }).collect(Collectors.toSet()));

       return response;
    }
}

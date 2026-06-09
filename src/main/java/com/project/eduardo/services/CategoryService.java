package com.project.eduardo.services;

import com.project.eduardo.dto.baseDTO.OrderItemDTO;
import com.project.eduardo.dto.request.CategoryDTOrequest;
import com.project.eduardo.dto.response.CategoryDTOresponse;
import com.project.eduardo.dto.response.ProductDTOresponse;
import com.project.eduardo.entities.Category;
import com.project.eduardo.repositories.CategoryRepository;
import com.project.eduardo.services.exceptions.ResourceNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {return repository.findAll();}

    public CategoryDTOresponse FindById(Long id) {
        Category obj = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Categoria não encontrada"
                        + id));
        CategoryDTOresponse response = new CategoryDTOresponse();
        response.setId(obj.getId());
        response.setName(obj.getName());
        return response;
    }

    @Transactional
    public CategoryDTOresponse insertCategory(Category obj) {
        CategoryDTOrequest request = new CategoryDTOrequest();
        request.setName(obj.getName());
        Category saved = repository.save(obj);
        CategoryDTOresponse response = new CategoryDTOresponse();
        response.setName(saved.getName());
        response.setId(saved.getId());
        return response;
    }
}

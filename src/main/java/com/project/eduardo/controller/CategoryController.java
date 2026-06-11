package com.project.eduardo.controller;

import com.project.eduardo.dto.request.CategoryDTOrequest;
import com.project.eduardo.dto.response.CategoryDTOresponse;
import com.project.eduardo.entities.Category;
import com.project.eduardo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTOresponse> findById(@PathVariable Long id){
        CategoryDTOresponse response = service.FindById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping(value = "/insert")
    public ResponseEntity<CategoryDTOresponse> insertCategory(@RequestBody CategoryDTOrequest obj){
        CategoryDTOresponse response = service.insertCategory(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}

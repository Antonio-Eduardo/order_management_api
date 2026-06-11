package com.project.eduardo.controller;

import com.project.eduardo.dto.request.ProductDTOrequest;
import com.project.eduardo.dto.response.ProductDTOresponse;
import com.project.eduardo.entities.Product;
import com.project.eduardo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTOresponse> findById(@PathVariable Long id){
        ProductDTOresponse obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping(value = "/insert")
    public ResponseEntity<ProductDTOresponse> insertProduto(@RequestBody ProductDTOrequest obj){
        ProductDTOresponse response = service.inserProduct(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}

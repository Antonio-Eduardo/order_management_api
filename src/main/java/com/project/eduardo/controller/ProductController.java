package com.project.eduardo.controller;

import com.project.eduardo.dto.request.ProductDTOrequest;
import com.project.eduardo.dto.response.ProductDTOresponse;
import com.project.eduardo.entities.Product;
import com.project.eduardo.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@Tag(name = "Produtos",description = "Todas as operações relacionadas aos Produtos")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Listar um produto pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public ResponseEntity<ProductDTOresponse> findById(@PathVariable Long id){
        ProductDTOresponse obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping(value = "/insert")
    @Operation(summary = "Inserir um produto")
    @ApiResponse(responseCode = "201", description = "Sucesos")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<ProductDTOresponse> insertProduto(@RequestBody ProductDTOrequest obj){
        ProductDTOresponse response = service.inserProduct(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}

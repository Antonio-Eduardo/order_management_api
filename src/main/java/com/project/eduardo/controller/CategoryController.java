package com.project.eduardo.controller;

import com.project.eduardo.dto.request.CategoryDTOrequest;
import com.project.eduardo.dto.response.CategoryDTOresponse;
import com.project.eduardo.entities.Category;
import com.project.eduardo.services.CategoryService;
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
@RequestMapping(value = "/category")
@Tag(name = "Categoria", description = "Todas as operações relacionadas as categorias")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    @Operation(summary = "Listar todas as categorias")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Listar uma categoria pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrado")
    public ResponseEntity<CategoryDTOresponse> findById(@PathVariable Long id){
        CategoryDTOresponse response = service.FindById(id);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping(value = "/insert")
    @Operation(summary = "Inserir uma categoria")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<CategoryDTOresponse> insertCategory(@RequestBody CategoryDTOrequest obj){
        CategoryDTOresponse response = service.insertCategory(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}

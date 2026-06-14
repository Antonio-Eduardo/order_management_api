package com.project.eduardo.controller;

import com.project.eduardo.dto.request.UserDTOrequest;
import com.project.eduardo.dto.response.UserDTOresponse;
import com.project.eduardo.entities.User;
import com.project.eduardo.services.UserService;
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
@RequestMapping(value = "/users")
@Tag(name = "Users", description = "Operações relacionadas aos usuários")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @Operation(summary = "Listar todos os usuários")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Listar um usuário pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    public ResponseEntity<UserDTOresponse> findById(@PathVariable Long id) {
        UserDTOresponse obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/insert")
    @Operation(summary = "Inserir um novo usuário")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de registro inválidos")
    public ResponseEntity<UserDTOresponse> insertUser(@RequestBody UserDTOrequest obj) {
        UserDTOresponse response = service.insertUser(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deletar um usuário pelo ID")
    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar um usuário pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    public ResponseEntity<UserDTOresponse> updateUser(@PathVariable Long id, @RequestBody UserDTOrequest obj){
        UserDTOresponse response = service.updateUser(id,obj);
        return ResponseEntity.ok().body(response);
    }
}

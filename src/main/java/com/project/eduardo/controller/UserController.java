package com.project.eduardo.controller;

import com.project.eduardo.dto.request.UserDTOrequest;
import com.project.eduardo.dto.response.UserDTOresponse;
import com.project.eduardo.entities.User;
import com.project.eduardo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTOresponse> findById(@PathVariable Long id) {
        UserDTOresponse obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<UserDTOresponse> insertUser(@RequestBody UserDTOrequest obj) {
        UserDTOresponse response = service.insertUser(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTOresponse> updateUser(@PathVariable Long id, @RequestBody UserDTOrequest obj){
        UserDTOresponse response = service.updateUser(id,obj);
        return ResponseEntity.ok().body(response);
    }
}

package com.project.eduardo.controller;

import com.project.eduardo.dto.request.OrderDTOrequest;
import com.project.eduardo.dto.response.OrderDTOresponse;
import com.project.eduardo.entities.Order;
import com.project.eduardo.enums.OrderStatus;
import com.project.eduardo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTOresponse> findById(@PathVariable Long id){
        OrderDTOresponse obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping(value = "/insert/{id}")
    public ResponseEntity<OrderDTOresponse> inserCategory(@RequestBody OrderDTOrequest orderDTO, @PathVariable Long id) {
        OrderDTOresponse response = service.orderInsert(orderDTO, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}

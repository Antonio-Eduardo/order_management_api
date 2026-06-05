package com.project.eduardo.controller;

import com.project.eduardo.dto.request.OrderDTOrequest;
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
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping
    public ResponseEntity<Order> inserCategory(@RequestBody OrderDTOrequest orderDTO){
        Order order = new Order();
        order.setOrderStatus(OrderStatus.valueOf(orderDTO.getOrderStatusId()));
        order.setMoment(Instant.now());

        Order save = service.orderInsert(order, orderDTO.getClientId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save).toUri();
        return ResponseEntity.created(uri).body(save);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> updateUser(@PathVariable Long id, @RequestBody OrderDTOrequest orderDTO){

         Order orderUpdate = service.updateOrder(orderDTO,id);

        return ResponseEntity.ok().body(orderUpdate);
    }
}

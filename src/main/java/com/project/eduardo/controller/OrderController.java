package com.project.eduardo.controller;

import com.project.eduardo.dto.request.OrderDTOrequest;
import com.project.eduardo.dto.response.OrderDTOresponse;
import com.project.eduardo.entities.Order;
import com.project.eduardo.enums.OrderStatus;
import com.project.eduardo.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@Tag(name = "Pedidos", description = "Todas as operações relacionadas aos pedidos")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    @Operation(summary = "Listar todos os pedidos")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    @Operation(summary = "Listar um pedido pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrado")
    public ResponseEntity<OrderDTOresponse> findById(@PathVariable Long id){
        OrderDTOresponse obj = service.FindById(id);
        return ResponseEntity.ok().body(obj);
    }
    @PostMapping(value = "/insert/{id}")
    @Operation(summary = "Inserir um pedido em um usuário identificado pelo ID")
    @ApiResponse(responseCode = "201", description = "Sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity<OrderDTOresponse> inserCategory(@RequestBody OrderDTOrequest orderDTO, @PathVariable Long id) {
        OrderDTOresponse response = service.orderInsert(orderDTO, id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}

package com.project.eduardo.controller;

import com.project.eduardo.dto.request.OrderItemDTOrequest;
import com.project.eduardo.dto.response.OrderItemDTOresponse;
import com.project.eduardo.entities.Order;
import com.project.eduardo.entities.OrderItem;
import com.project.eduardo.entities.Product;
import com.project.eduardo.entities.pk.OrderItemPK;
import com.project.eduardo.services.OrderItemService;
import com.project.eduardo.services.OrderService;
import com.project.eduardo.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order-item")
@Tag(name = "Itens do Pedido", description = "Todas as operações relacionadas aos itens dos pedidos")
public class OrderItemController {

    @Autowired
    OrderItemService service;

    @GetMapping
    @Operation(summary = "Listar todos os itens dos pedidos")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<List<OrderItem>> getOrderItems(){
        return ResponseEntity.ok().body(service.getOrderItems());
    }

    @GetMapping(value = "/order-items/{orderId}/{productId}")
    @Operation(summary = "Listar um item de um pedido pelo ID(Foreign Key)")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    @ApiResponse(responseCode = "404", description = "Não encontrado")
    public ResponseEntity<OrderItemDTOresponse> findById(
            @PathVariable Long order,
            @PathVariable Long product) {
        OrderItemDTOresponse response = service.FindById(order,product);

        return ResponseEntity.ok().body(response);
    }
}

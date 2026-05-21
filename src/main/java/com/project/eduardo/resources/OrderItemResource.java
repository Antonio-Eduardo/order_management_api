package com.project.eduardo.resources;

import com.project.eduardo.dto.OrderItemDTO;
import com.project.eduardo.entities.Order;
import com.project.eduardo.entities.OrderItem;
import com.project.eduardo.entities.Product;
import com.project.eduardo.services.OrderItemService;
import com.project.eduardo.services.OrderService;
import com.project.eduardo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/order-item")
public class OrderItemResource {

    @Autowired
    OrderItemService service;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderItem>> getOrderItems(){
        return ResponseEntity.ok().body(service.getOrderItems());
    }
    @GetMapping(value = "/order-items/{orderId}/{productId}")
    public ResponseEntity<OrderItem> findById(
            @PathVariable Long order,
            @PathVariable Long product) {

        OrderItem obj = service.FindById(order,product);

        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<OrderItem> insertOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        Product product = productService.FindById(orderItemDTO.getProductId());
        Order order = orderService.FindById(orderItemDTO.getOrderId());

        BigDecimal price = product.getPrice();
        Integer quantity = orderItemDTO.getQuantity();

        OrderItem orderItem = new OrderItem(order,product,quantity,price);

        order.getItems().add(orderItem);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderItem).toUri();
        return ResponseEntity.created(uri).body(orderItem);
    }

}

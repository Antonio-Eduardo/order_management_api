package com.project.eduardo.services;

import com.project.eduardo.entities.Order;
import com.project.eduardo.entities.OrderItem;
import com.project.eduardo.entities.Product;
import com.project.eduardo.entities.pk.OrderItemPK;
import com.project.eduardo.repositories.OrderItemRepository;
import com.project.eduardo.repositories.OrderRepository;
import com.project.eduardo.repositories.ProductRepository;
import com.project.eduardo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public OrderItem inserOrderItem(OrderItem oi){
        oi.getOrder().getItems().add(oi);
        orderRepository.save(oi.getOrder());
        return orderItemRepository.save(oi);
    }

    public List<OrderItem> getOrderItems(){
        return orderItemRepository.findAll();
    }

    public OrderItem FindById(Long orderId, Long productId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(orderId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException(productId));
        OrderItemPK orderItemPK = new OrderItemPK(order,product);

        return orderItemRepository.findById(orderItemPK).orElseThrow(() -> new ResourceNotFoundException(orderItemPK));

    }
}

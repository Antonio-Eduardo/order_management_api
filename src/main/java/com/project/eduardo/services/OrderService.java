package com.project.eduardo.services;

import com.project.eduardo.dto.request.OrderDTOrequest;
import com.project.eduardo.dto.request.OrderItemDTOrequest;
import com.project.eduardo.dto.response.OrderDTOresponse;
import com.project.eduardo.dto.response.OrderItemDTOresponse;
import com.project.eduardo.dto.response.UserDTOresponse;
import com.project.eduardo.entities.Order;
import com.project.eduardo.entities.OrderItem;
import com.project.eduardo.entities.Product;
import com.project.eduardo.entities.User;
import com.project.eduardo.enums.OrderStatus;
import com.project.eduardo.repositories.OrderRepository;
import com.project.eduardo.repositories.ProductRepository;
import com.project.eduardo.repositories.UserRepository;
import com.project.eduardo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> findAll(){
        return repository.findAll();
    }
    public OrderDTOresponse FindById(Long id){
        Order orderfind = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        UserDTOresponse client = new UserDTOresponse();
        client.setName(orderfind.getClient().getName());
        client.setEmail(orderfind.getClient().getEmail());
        client.setId(orderfind.getClient().getId());
        client.setPhone(orderfind.getClient().getPhone());

        OrderDTOresponse response = new OrderDTOresponse();
        response.setId(orderfind.getId());
        response.setClient(client);
        response.setMoment(orderfind.getMoment());
        response.setOrderStatus(orderfind.getOrderStatus().getCode());
        response.setItems(orderfind.getItems().stream().map(orderItem -> {
            OrderItemDTOresponse orderItemDTO = new OrderItemDTOresponse();
            orderItemDTO.setOrderId(orderItem.getOrder().getId());
            orderItemDTO.setProductId(orderItem.getProduct().getId());
            orderItemDTO.setPrice(orderItem.getPrice());
            orderItemDTO.setQuantity(orderItem.getQuantity());
            return orderItemDTO;
        }).collect(Collectors.toSet()));
        return response;
    }
    @Transactional
    public OrderDTOresponse orderInsert(OrderDTOrequest obj, Long clientId){
        User client = userRepository.findById(clientId).orElseThrow(() ->
                new ResourceNotFoundException("Cliente não encontrado ! ID:" + clientId));

        Order newOrder = new Order();
        newOrder.setOrderStatus(OrderStatus.valueOf(obj.getOrderStatus()));
        newOrder.setMoment(Instant.now());
        newOrder.setClient(client);

        newOrder.setItems(obj.getItems().stream().map(orderItemDTO -> {
            Product productFind = productRepository.findById(orderItemDTO.getProductId()).orElseThrow(() ->
                    new ResourceNotFoundException("Produto não encontrado ! ID:" + orderItemDTO.getProductId()));

            return new OrderItem(newOrder, productFind, orderItemDTO.quantity, productFind.getPrice());
        }).collect(Collectors.toSet()));

        Order savedOrder = repository.save(newOrder);

        UserDTOresponse clientResponse = new UserDTOresponse();
        clientResponse.setName(client.getName());
        clientResponse.setId(client.getId());
        clientResponse.setEmail(client.getEmail());
        clientResponse.setPhone(client.getPhone());


        OrderDTOresponse response = new OrderDTOresponse();
        response.setOrderStatus(savedOrder.getOrderStatus().getCode());
        response.setId(savedOrder.getId());
        response.setMoment(savedOrder.getMoment());
        response.setClient(clientResponse);
        response.setItems(savedOrder.getItems().stream().map(orderItem -> {
            OrderItemDTOresponse orderItemDTOresponse = new OrderItemDTOresponse();
            orderItemDTOresponse.setOrderId(orderItem.getOrder().getId());
            orderItemDTOresponse.setProductId(orderItem.getProduct().getId());
            orderItemDTOresponse.setPrice(orderItem.getPrice());
            orderItemDTOresponse.setQuantity(orderItem.getQuantity());
            return orderItemDTOresponse;
        }).collect(Collectors.toSet()));


        return response;
    }
}

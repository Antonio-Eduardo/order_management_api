package com.project.eduardo.services;

import com.project.eduardo.dto.OrderDTO;
import com.project.eduardo.dto.OrderItemDTO;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Order FindById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public Order orderInsert(Order obj, Long clientId){
        User client = userRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException(clientId));

        obj.setClient(client);
        return repository.save(obj);
    }
    public void updateData(Order orderfind, OrderDTO orderDTO ){
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(itemDTO.getProductId()));

            OrderItem item = new OrderItem();

            item.setOrder(orderfind);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());

            orderfind.getItems().add(item);
        }
        orderfind.getTotal();
        repository.save(orderfind);
    }
    public Order updateOrder(OrderDTO orderDTO, Long id){
        Order orderfind = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(orderfind,orderDTO);
        return repository.save(orderfind);
    }
}

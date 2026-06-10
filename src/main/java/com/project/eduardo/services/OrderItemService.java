package com.project.eduardo.services;

import com.project.eduardo.dto.response.OrderItemDTOresponse;
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


@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public List<OrderItem> getOrderItems(){
        return orderItemRepository.findAll();
    }
    public OrderItemDTOresponse FindById(Long orderId, Long productId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order não encontrada! ID:"+orderId));

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado! ID:" +productId));

        OrderItemPK pk = new OrderItemPK(order,product);

       OrderItem orderItemFind = orderItemRepository.findById(pk).orElseThrow(() -> new ResourceNotFoundException("Order item não encontrado! ID:" + pk));

        OrderItemDTOresponse response = new OrderItemDTOresponse();
        response.setOrderId(orderItemFind.getOrder().getId());
        response.setProductId(orderItemFind.getProduct().getId());
        response.setQuantity(orderItemFind.getQuantity());
        response.setPrice(orderItemFind.getPrice());

        return response;

    }
}

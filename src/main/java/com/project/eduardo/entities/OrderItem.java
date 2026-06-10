package com.project.eduardo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.eduardo.entities.pk.OrderItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
@Entity
@Table(name = "tb_order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private BigDecimal price;

    public OrderItem(Order order, Product product, Integer quantity, BigDecimal price) {
        id.setOrder(order);
        id.setProduct(product);
        this.price = price;
        this.quantity = quantity;
    }


    @JsonIgnore
    public Order getOrder(){
        return id.getOrder();
    }


    public void setOrder(Order order){
        id.setOrder(order);
    }

    public BigDecimal getSubTotal(){
        return price.multiply(BigDecimal.valueOf(quantity));
    }
    public Product getProduct(){
        return id.getProduct();
    }
    public void setProduct(Product product){
        id.setProduct(product);
    }
}


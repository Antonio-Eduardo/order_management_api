package com.project.eduardo.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTOresponse {
    public Long productId;
    public Long orderId;
    public BigDecimal price;
    public Integer quantity;
}

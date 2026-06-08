package com.project.eduardo.dto.baseDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    public Long productId;
    public Long orderId;
    public BigDecimal price;
    public Integer quantity;
}

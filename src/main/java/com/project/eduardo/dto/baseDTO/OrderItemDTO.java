package com.project.eduardo.dto.baseDTO;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long productId;
    private Long orderId;
    private Integer quantity;
}

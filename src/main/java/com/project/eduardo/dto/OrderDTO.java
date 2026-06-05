package com.project.eduardo.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {
    private Long clientId;
    private int orderStatusId;
    private Set<OrderItemDTO> items = new HashSet<>();

}

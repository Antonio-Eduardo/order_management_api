package com.project.eduardo.dto.request;

import com.project.eduardo.dto.baseDTO.OrderItemDTO;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTOrequest {
    private Long clientId;
    private int orderStatusId;
    private Set<OrderItemDTO> items = new HashSet<>();

}

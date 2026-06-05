package com.project.eduardo.dto.response;

import com.project.eduardo.dto.baseDTO.OrderItemDTO;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTOresponse {

    public Long id;
    public Long clientId;
    public Integer orderStatusId;
    private Set<OrderItemDTO> items = new HashSet<>();
}

package com.project.eduardo.dto.response;

import com.project.eduardo.dto.baseDTO.OrderItemDTO;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTOresponse {

    public Long id;
    public Instant moment;
    public Integer orderStatus;
    public UserDTOresponse client;
    private Set<OrderItemDTO> items = new HashSet<>();
}

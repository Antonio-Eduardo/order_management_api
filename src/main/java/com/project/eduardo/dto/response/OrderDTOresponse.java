package com.project.eduardo.dto.response;

import com.project.eduardo.dto.request.OrderItemDTOrequest;
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
    private Set<OrderItemDTOresponse> items = new HashSet<>();
}

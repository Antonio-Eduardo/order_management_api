package com.project.eduardo.dto.request;

import com.project.eduardo.dto.baseDTO.OrderItemDTO;
import com.project.eduardo.dto.response.UserDTOresponse;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTOrequest {
    public Instant moment;
    public Integer orderStatus;
    public UserDTOresponse client;
    public Set<OrderItemDTO> items = new HashSet<>();

}

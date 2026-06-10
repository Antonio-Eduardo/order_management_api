package com.project.eduardo.dto.request;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTOrequest {
    public Integer orderStatus;
    public Set<OrderItemDTOrequest> items = new HashSet<>();

}

package com.project.eduardo.dto.request;

import com.project.eduardo.dto.baseDTO.OrderItemDTO;
import com.project.eduardo.dto.response.CategoryDTOresponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDTOrequest {
    public String name;
    public String description;
    public BigDecimal price;
    public String imgURL;

    public Set<OrderItemDTO> items = new HashSet<>();
    public Set<CategoryDTOresponse> categories = new HashSet<>();
}

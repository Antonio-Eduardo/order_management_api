package com.project.eduardo.dto.response;

import com.project.eduardo.dto.baseDTO.OrderItemDTO;
import com.project.eduardo.dto.request.CategoryDTOrequest;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDTOresponse {

    public Long id;
    public String name;
    public String description;
    public BigDecimal price;
    public String imgUrl;
    public Set<OrderItemDTO> items = new HashSet<>();
    public Set<CategoryDTOresponse> categories = new HashSet<>();
}

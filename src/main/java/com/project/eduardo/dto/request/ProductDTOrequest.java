package com.project.eduardo.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ProductDTOrequest {
    public String name;
    public String description;
    public BigDecimal pricel;
    public String imgURL;

    public Set<CategoryDTOrequest> items;
}

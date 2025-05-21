package com.nextstep.ecomerce.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private double price;
    private int stock;
    private String description;
}

package com.furkan.enoca.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    @NotEmpty(message = "Product name cannot be empty")
    private String name;

    @Min(value = 0, message = "Product unit price cannot be less then 0")
    private double unitPrice;

    private String description;

    @NotEmpty(message = "Product name cannot be empty")
    private String code;

    @Min(value = 0, message = "Product unit price cannot be less then 0")
    private int stock;
}

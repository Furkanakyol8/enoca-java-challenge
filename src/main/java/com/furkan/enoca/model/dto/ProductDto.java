package com.furkan.enoca.model.dto;

import com.furkan.enoca.model.entity.Product;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private UUID id;

    private String name;

    private String code;

    private double unitPrice;

    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.code = product.getCode();
        this.description = product.getDescription();
        this.unitPrice = product.getUnitPrice();
        this.createdAt = product.getCreatedAt();
        this.updatedAt = product.getUpdatedAt();
    }

}

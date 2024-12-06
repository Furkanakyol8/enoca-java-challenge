package com.furkan.enoca.model.entity;


import com.furkan.enoca.exception.NotFoundException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@SuperBuilder
@Entity
@Table(name = "products")
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Base {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice = 0.0;

    @Column(name = "description")
    private String description;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "stock", nullable = false)
    private int stock = 0;

    public void checkHasStock(int quantity) {
        if(quantity > stock){
            throw new NotFoundException("Stock not found for product with id: " + getId());
        }
    }
}



package com.furkan.enoca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "cart_items")
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

public class CartItem extends Base{

    @Column
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Double getTotalPrice(){
        return getUnitPrice() * quantity;
    }

    public Double getUnitPrice(){
        return product.getUnitPrice();
    }
}

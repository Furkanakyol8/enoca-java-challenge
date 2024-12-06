package com.furkan.enoca.model.entity;

import com.furkan.enoca.exception.NotFoundException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Table(name = "order_items")
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends Base {

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column
    private double unitPrice;

    @Column
    private int quantity;

    public OrderItem(Order order, CartItem cartItem) {
        cartItem.getProduct().checkHasStock(cartItem.getQuantity());
        this.order = order;
        this.product = cartItem.getProduct();
        this.unitPrice = cartItem.getUnitPrice();
        this.quantity = cartItem.getQuantity();
    }
}

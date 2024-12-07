package com.furkan.enoca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@SuperBuilder
@Entity
@Table(name = "order_items")
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("deleted_at is null")
@SQLDelete(sql = "UPDATE order_items SET deleted_at = now() WHERE id = ?")
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

package com.furkan.enoca.model.entity;

import com.furkan.enoca.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

public class Order extends Base {

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();

    public void placeOrder(Customer customer) {
        Cart customerCart = customer.getCart();

        this.items = customerCart
                .getItems()
                .stream()
                .map(cartItem -> new OrderItem(this, cartItem))
                .toList();

        this.customer = customer;
        this.status = OrderStatus.PREPARING;
    }
}

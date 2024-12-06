package com.furkan.enoca.model.entity;


import com.furkan.enoca.exception.NotFoundException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends Base{

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();

    @Column(nullable = false)
    private Double totalPrice = 0.0;


    public Cart(Customer customer) {
        this.customer = customer;
    }

    public void addToCart(CartItem cartItem) {
        items.add(cartItem);
        totalPrice += cartItem.getTotalPrice();
        updated();
    }

    public void removeFromCart(UUID productId) {
        CartItem cartItem = findCartItem(productId);
        removeFromCart(cartItem);
    }

    private void removeFromCart(CartItem cartItem) {
        items.remove(cartItem);
        totalPrice -= cartItem.getTotalPrice();
        updated();
    }

    public void addQuantityToItem(UUID productId) {
        CartItem cartItem = findCartItem(productId);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        totalPrice += cartItem.getUnitPrice();
        updated();
    }

    public void removeQuantityFromItem(UUID productId) {
        CartItem cartItem = findCartItem(productId);
        if(cartItem.getQuantity() == 1) {
            removeFromCart(cartItem);
            return;
        }
        cartItem.setQuantity(cartItem.getQuantity() - 1);
        totalPrice -= cartItem.getUnitPrice();
        updated();
    }

    public void emptyCart() {
        items.clear();
        totalPrice = 0.0;
        updated();
    }

    private CartItem findCartItem(UUID productId) {
        Optional<CartItem> foundCartItem = items
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (foundCartItem.isEmpty()) {
            throw new NotFoundException("Product with id " + productId + " not found in cart");
        }

        return foundCartItem.get();
    }

    private void updated(){
        setUpdatedAt(LocalDateTime.now());
    }
}

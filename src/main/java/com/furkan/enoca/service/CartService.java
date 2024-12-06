package com.furkan.enoca.service;

import com.furkan.enoca.model.entity.Cart;

import java.util.UUID;

public interface CartService {

    Cart getMyCart();

    void emptyMyCart();

    void addProductToCart(UUID productId);

    void removeProductFromCart(UUID productId);

    void addQuantityToCartItem(UUID productId);

    void removeQuantityFromCartItem(UUID productId);
}

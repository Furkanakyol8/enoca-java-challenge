package com.furkan.enoca.service.impl;

import com.furkan.enoca.exception.NotFoundException;
import com.furkan.enoca.model.entity.Cart;
import com.furkan.enoca.model.entity.CartItem;
import com.furkan.enoca.model.entity.Product;
import com.furkan.enoca.repository.CartRepository;
import com.furkan.enoca.repository.ProductRepository;
import com.furkan.enoca.security.helper.UserHelper;
import com.furkan.enoca.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart getMyCart() {
        return UserHelper.getLoggedCustomer().getCart();
    }

    @Override
    public void emptyMyCart() {
        Cart customerCart = UserHelper.getLoggedCustomer().getCart();
        customerCart.emptyCart();

        cartRepository.save(customerCart);
    }

    @Override
    public void addProductToCart(UUID productId) {
        Cart customerCart = UserHelper.getLoggedCustomer().getCart();

        Product foundProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));
        CartItem cartItem = CartItem.builder()
                .cart(customerCart)
                .product(foundProduct)
                .quantity(1)
                .build();

        customerCart.addToCart(cartItem);

        cartRepository.save(customerCart);
    }

    @Override
    public void removeProductFromCart(UUID productId) {
        Cart customerCart = UserHelper.getLoggedCustomer().getCart();

        customerCart.removeFromCart(productId);

        cartRepository.save(customerCart);
    }

    @Override
    public void addQuantityToCartItem(UUID productId) {
        Cart customerCart = UserHelper.getLoggedCustomer().getCart();

        customerCart.addQuantityToItem(productId);

        cartRepository.save(customerCart);
    }

    @Override
    public void removeQuantityFromCartItem(UUID productId) {
        Cart customerCart = UserHelper.getLoggedCustomer().getCart();

        customerCart.removeQuantityFromItem(productId);

        cartRepository.save(customerCart);
    }

}

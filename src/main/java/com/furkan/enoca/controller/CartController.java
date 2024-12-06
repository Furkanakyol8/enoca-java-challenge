package com.furkan.enoca.controller;

import com.furkan.enoca.model.dto.CartDto;
import com.furkan.enoca.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartDto> getMyCart() {
        return ResponseEntity.ok(new CartDto(cartService.getMyCart()));
    }

    @PatchMapping
    public ResponseEntity<Void> emptyMyCart() {
        cartService.emptyMyCart();

        return ResponseEntity.ok().build();
    }

    @PostMapping("/item/{productId}")
    public ResponseEntity<Void> addProductToCart(@PathVariable UUID productId) {
        cartService.addProductToCart(productId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable UUID productId) {
        cartService.removeProductFromCart(productId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/item/add-quantity/{productId}")
    public ResponseEntity<Void> addQuantityToItem(@PathVariable UUID productId) {
        cartService.addQuantityToCartItem(productId);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/item/remove-quantity/{productId}")
    public ResponseEntity<Void> removeQuantityFromItem(@PathVariable UUID productId) {
        cartService.removeQuantityFromCartItem(productId);

        return ResponseEntity.ok().build();
    }

}

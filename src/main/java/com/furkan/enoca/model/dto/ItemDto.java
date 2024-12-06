package com.furkan.enoca.model.dto;

import com.furkan.enoca.model.entity.CartItem;
import com.furkan.enoca.model.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ItemDto {
    private ProductDto product;
    private int quantity;
    private double unitPrice;

    public ItemDto(CartItem cartItem) {
        product = new ProductDto(cartItem.getProduct());
        quantity = cartItem.getQuantity();
        unitPrice = cartItem.getUnitPrice();
    }

    public ItemDto(OrderItem orderItem) {
        product = new ProductDto(orderItem.getProduct());
        quantity = orderItem.getQuantity();
        unitPrice = orderItem.getUnitPrice();
    }
}

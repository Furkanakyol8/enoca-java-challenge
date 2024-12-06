package com.furkan.enoca.model.dto;

import com.furkan.enoca.model.entity.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDto {
    private CustomerDto customer;
    private List<ItemDto> items;
    private Double totalPrice;

    public CartDto(Cart cart) {
        customer = new CustomerDto(cart.getCustomer());
        items = cart.getItems()
                .stream()
                .map(ItemDto::new)
                .toList();
        totalPrice = cart.getTotalPrice();
    }
}

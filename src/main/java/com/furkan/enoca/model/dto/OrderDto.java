package com.furkan.enoca.model.dto;

import com.furkan.enoca.model.entity.Order;
import com.furkan.enoca.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {
    private UUID id;
    private OrderStatus status;
    private CustomerDto customer;
    private List<ItemDto> items;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
        this.customer = new CustomerDto(order.getCustomer());
        items = order
                .getItems()
                .stream()
                .map(ItemDto::new)
                .toList();
    }
}

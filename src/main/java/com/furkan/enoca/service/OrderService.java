package com.furkan.enoca.service;

import com.furkan.enoca.model.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDto placeOrder();

    OrderDto findMyOrderById(UUID id);

    List<OrderDto> findMyAllOrders();
}

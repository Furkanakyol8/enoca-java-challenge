package com.furkan.enoca.service.impl;

import com.furkan.enoca.exception.NotFoundException;
import com.furkan.enoca.model.dto.OrderDto;
import com.furkan.enoca.model.entity.Customer;
import com.furkan.enoca.model.entity.Order;
import com.furkan.enoca.repository.OrderRepository;
import com.furkan.enoca.security.helper.UserHelper;
import com.furkan.enoca.service.CartService;
import com.furkan.enoca.service.OrderService;
import com.furkan.enoca.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    @Override
    public OrderDto placeOrder() {
        Customer loggedCustomer = UserHelper.getLoggedCustomer();

        Order order = new Order();
        order.placeOrder(loggedCustomer);

        orderRepository.save(order);
        cartService.emptyMyCart();

        order.getItems().forEach(item -> {
            productService.removeFromStock(item.getProduct(), item.getQuantity());
        });

        return new OrderDto(order);
    }

    @Override
    public OrderDto findMyOrderById(UUID id) {
        Customer loggedCustomer = UserHelper.getLoggedCustomer();

        Order foundOrder = orderRepository.findMyOrderById(loggedCustomer.getId(), id)
                .orElseThrow(() -> new NotFoundException("Order not found by code: " + id));

        return new OrderDto(foundOrder);
    }

    @Override
    public List<OrderDto> findMyAllOrders() {
        Customer loggedCustomer = UserHelper.getLoggedCustomer();

        return orderRepository.findMyAllOrders(loggedCustomer.getId())
                .stream()
                .map(OrderDto::new)
                .toList(); // bunu da kullanabilirdik loggedCustomer.getOrders()
    }
}

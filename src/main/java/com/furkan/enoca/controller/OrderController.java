package com.furkan.enoca.controller;


import com.furkan.enoca.model.dto.OrderDto;
import com.furkan.enoca.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.placeOrder());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getMyOrderById(@PathVariable UUID id){
        return ResponseEntity.ok(orderService.findMyOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getMyAllOrders(){
        return ResponseEntity.ok(orderService.findMyAllOrders());
    }

}

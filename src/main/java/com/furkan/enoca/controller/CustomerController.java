package com.furkan.enoca.controller;

import com.furkan.enoca.model.dto.CreateCustomerRequest;
import com.furkan.enoca.model.dto.CustomerDto;
import com.furkan.enoca.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

}

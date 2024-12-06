package com.furkan.enoca.service;

import com.furkan.enoca.model.dto.CreateCustomerRequest;
import com.furkan.enoca.model.dto.CustomerDto;

public interface CustomerService {

    CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest);
}

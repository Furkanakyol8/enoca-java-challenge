package com.furkan.enoca.service.impl;

import com.furkan.enoca.exception.AlreadyExistException;
import com.furkan.enoca.model.dto.CreateCustomerRequest;
import com.furkan.enoca.model.dto.CustomerDto;
import com.furkan.enoca.model.entity.Cart;
import com.furkan.enoca.model.entity.Customer;
import com.furkan.enoca.repository.CustomerRepository;
import com.furkan.enoca.repository.UserRepository;
import com.furkan.enoca.service.CustomerService;
import com.furkan.enoca.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = Customer.builder()
                .email(createCustomerRequest.getEmail())
                .firstName(createCustomerRequest.getFirstName())
                .lastName(createCustomerRequest.getLastName())
                .password(passwordEncoder.encode(createCustomerRequest.getPassword()))
                .phone(createCustomerRequest.getPhone())
                .build();

        if (userRepository.existsByEmail(customer.getEmail())) {
            throw new AlreadyExistException("User already exists with email: " + customer.getEmail());
        }

        Cart customerCart = new Cart(customer);
        customer.setCart(customerCart);

        customerRepository.save(customer);
        return new CustomerDto(customer);
    }



}

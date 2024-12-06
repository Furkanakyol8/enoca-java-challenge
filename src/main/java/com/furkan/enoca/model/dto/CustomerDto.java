package com.furkan.enoca.model.dto;

import com.furkan.enoca.model.entity.Customer;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }

}

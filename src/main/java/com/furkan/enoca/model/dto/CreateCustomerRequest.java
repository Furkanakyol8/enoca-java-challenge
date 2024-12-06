package com.furkan.enoca.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;

}

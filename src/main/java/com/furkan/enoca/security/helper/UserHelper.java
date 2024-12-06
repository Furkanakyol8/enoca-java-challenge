package com.furkan.enoca.security.helper;

import com.furkan.enoca.exception.CustomAuthenticationException;
import com.furkan.enoca.model.entity.Customer;
import com.furkan.enoca.model.entity.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class UserHelper {
    public User getLoggedUser(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return (User) authentication.getDetails();
        } else {
            return null;
        }
    }

    public Customer getLoggedCustomer(){
        User loggedUser = getLoggedUser();
        if (loggedUser == null) {
            return null;
        }

        if(Customer.class.isAssignableFrom(loggedUser.getClass())){
            return (Customer) loggedUser;
        }

        throw new CustomAuthenticationException("User not customer!");
    }
}
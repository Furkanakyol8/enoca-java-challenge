package com.furkan.enoca.model.dto;

import com.furkan.enoca.model.entity.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDto {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String email;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}

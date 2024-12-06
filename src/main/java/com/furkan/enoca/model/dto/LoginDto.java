package com.furkan.enoca.model.dto;

import com.furkan.enoca.model.entity.User;
import lombok.Getter;

@Getter
public class LoginDto {
    private final UserDto user;
    private final String token;

    public LoginDto(User user, String token) {
        this.user = new UserDto(user);
        this.token = token;
    }
}

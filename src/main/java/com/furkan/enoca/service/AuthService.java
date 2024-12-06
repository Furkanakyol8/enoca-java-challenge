package com.furkan.enoca.service;

import com.furkan.enoca.model.dto.LoginDto;
import com.furkan.enoca.model.dto.LoginRequest;
import com.furkan.enoca.model.dto.UserDto;

public interface AuthService {
    LoginDto login(LoginRequest loginRequest);

    UserDto me();
}

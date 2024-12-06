package com.furkan.enoca.service.impl;

import com.furkan.enoca.exception.CustomAuthenticationException;
import com.furkan.enoca.model.dto.LoginDto;
import com.furkan.enoca.model.dto.LoginRequest;
import com.furkan.enoca.model.dto.UserDto;
import com.furkan.enoca.model.entity.User;
import com.furkan.enoca.security.helper.JwtHelper;
import com.furkan.enoca.security.helper.UserHelper;
import com.furkan.enoca.service.AuthService;
import com.furkan.enoca.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;

    @Override
    public LoginDto login(LoginRequest loginRequest) {
        User foundUser = userService.findByEmail(loginRequest.getEmail());

        if(!passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPassword())) {
            throw new CustomAuthenticationException("Wrong password!");
        }

        String token = jwtHelper.generateToken(foundUser);

        return new LoginDto(foundUser, token);
    }

    @Override
    public UserDto me(){
        return new UserDto(UserHelper.getLoggedUser());
    }
}

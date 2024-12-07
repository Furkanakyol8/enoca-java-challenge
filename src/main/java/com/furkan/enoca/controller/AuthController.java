package com.furkan.enoca.controller;

import com.furkan.enoca.model.dto.LoginDto;
import com.furkan.enoca.model.dto.LoginRequest;
import com.furkan.enoca.model.dto.UserDto;
import com.furkan.enoca.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(){
        return ResponseEntity.ok(authService.me());
    }
}

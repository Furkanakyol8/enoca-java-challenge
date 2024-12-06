package com.furkan.enoca.service;

import com.furkan.enoca.model.entity.User;

public interface UserService {
    User findByEmail(String email);
}

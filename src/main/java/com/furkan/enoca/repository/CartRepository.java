package com.furkan.enoca.repository;

import com.furkan.enoca.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {



}

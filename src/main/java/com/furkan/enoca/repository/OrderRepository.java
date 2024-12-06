package com.furkan.enoca.repository;

import com.furkan.enoca.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("""
            SELECT o FROM Order o
            LEFT JOIN o.customer c
            WHERE c.id = :customerId and o.id = :id
            """)
    Optional<Order> findMyOrderById(UUID customerId, UUID id);

    @Query("""
            SELECT o FROM Order o
            LEFT JOIN o.customer c
            WHERE c.id = :customerId
            """)
    List<Order> findMyAllOrders(UUID customerId);
}

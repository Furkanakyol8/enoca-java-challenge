package com.furkan.enoca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.util.List;

@SuperBuilder
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@EqualsAndHashCode(of = {}, callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SQLRestriction("deleted_at is null")
@SQLDelete(sql = "UPDATE customers SET deleted_at = now() WHERE id = ?")
public class Customer extends User {

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;
}

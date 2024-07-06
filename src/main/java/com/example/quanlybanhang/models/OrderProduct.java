package com.example.quanlybanhang.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Đơn hàng
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    private Date createAt;
    private Date editAt;
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_orders",
            joinColumns = {@JoinColumn(name = "order_product_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> productSet;
}

package com.example.quanlybanhang.service;

import com.example.quanlybanhang.models.OrderProduct;

import java.util.List;
import java.util.Optional;

public interface OrderProductService {

    List<OrderProduct> findAll();

    Optional<OrderProduct> findById(Long id);

    void save(OrderProduct orderProduct);

    void delete(Long id);
}

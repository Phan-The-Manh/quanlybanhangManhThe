package com.example.quanlybanhang.service.impl;

import com.example.quanlybanhang.models.OrderProduct;
import com.example.quanlybanhang.repository.OrderProductRepository;
import com.example.quanlybanhang.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    public List<OrderProduct> findAll() {
        return orderProductRepository.findAll();
    }

    @Override
    public Optional<OrderProduct> findById(Long id) {
        return orderProductRepository.findById(id);
    }

    @Override
    public void save(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    @Override
    public void delete(Long id) {
        orderProductRepository.deleteById(id);
    }
}

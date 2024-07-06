package com.example.quanlybanhang.service;

import com.example.quanlybanhang.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void delete(Long id);

    Set<Product> findAllByIdProductIn(List<Long> id);

    List<Product> getAllProduct();

    void validateProduct(Product product) throws Exception;
}

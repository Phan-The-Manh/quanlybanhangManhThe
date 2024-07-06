package com.example.quanlybanhang.service.impl;

import com.example.quanlybanhang.models.Product;
import com.example.quanlybanhang.repository.ProductRepository;
import com.example.quanlybanhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Set<Product> findAllByIdProductIn(List<Long> id) {
        return productRepository.findAllByIdProductIn(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    @Override
    public void validateProduct(Product product) throws Exception {
        if (null == product.getProductName() || "".equals(product.getProductName())) {
            throw new Exception("Tên sản phẩm không được để trống");
        }
        if (null == product.getPrice() || "".equals(product.getPrice())) {
            throw new Exception("Giá sản phẩm không được để trống");
        }
        if (product.getQuantity() <= 0) {
            throw new Exception("Số lượng sản phẩm phải lớn hơn 0");
        }
    }
}

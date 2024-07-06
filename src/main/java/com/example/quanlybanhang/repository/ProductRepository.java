package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findAllByIdProductIn(List<Long> id);

    @Modifying
    @Query(value = "select * from product where is_delete = false and status = 'Hoạt động' and quantity > 0", nativeQuery = true)
    List<Product> getAllProduct();
}

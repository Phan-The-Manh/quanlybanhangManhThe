package com.example.quanlybanhang.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Coi như đây là khoảng đánh giá từ 1 đến 5 sao
    private String type;
    // Id của sản phẩm được đánh giá
    private Long productId;
    // Id của người mua hàng
    private Long idUser;
}

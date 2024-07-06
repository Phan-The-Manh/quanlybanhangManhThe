package com.example.quanlybanhang.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// Người dùng
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
    private Date dateOfBirth;
    private String phone;
    private String pin;
    private String status;
    private String role; // Admin, Người mua, Người bán
    private boolean buyer; // nếu = true là Người mua, nếu = false là Người bán
}

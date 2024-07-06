package com.example.quanlybanhang.service;

import com.example.quanlybanhang.models.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    void save(User user) throws Exception;

    void delete(Long id);

    void checkLogin(String username, String password) throws Exception;

    Optional<User> findUserByUsername(String username);

    void resetPassword(String username, String pin, String newPassword, String confirmPassword) throws Exception;

    void checkRoleAdmin(Long idUser) throws Exception;

    void validateUser(User user) throws Exception;

    void checkBannerUser(String username) throws Exception;
}

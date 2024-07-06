package com.example.quanlybanhang.service.impl;

import com.example.quanlybanhang.constant.Constants;
import com.example.quanlybanhang.models.User;
import com.example.quanlybanhang.repository.UserRepository;
import com.example.quanlybanhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) throws Exception {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void checkLogin(String username, String password) throws Exception {
        Optional<User> user = findUserByUsername(username);
        if (user.isEmpty()) {
            throw new Exception("Người dùng không tồn tại");
        }
        if (!user.get().getPassword().equals(password)) {
            throw new Exception("Mật khẩu không đúng");
        }
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void resetPassword(String username, String pin, String newPassword, String confirmPassword) throws Exception {
        Optional<User> user = findUserByUsername(username);
        if (user.isEmpty()) {
            throw new Exception("Người dùng không tồn tại");
        }
        if (!user.get().getPin().equals(pin)) {
            throw new Exception("Sai mã pin");
        }
        if (null == newPassword || "".equals(newPassword)) {
            throw new Exception("Bạn chưa nhập mật khẩu mới");
        } else if (newPassword.length() > 32 || newPassword.length() < 6) {
            throw new Exception("Mật khẩu phải lớn hơn 6 hoặc nhỏ hơn 32 kí tự");
        }
        if (!newPassword.equals(confirmPassword)) {
            throw new Exception("Xác nhận lại mật khẩu không đúng");
        }
        user.get().setPassword(newPassword);
        user.get().setConfirmPassword(confirmPassword);
        userRepository.save(user.get());
    }

    @Override
    public void checkRoleAdmin(Long idUser) throws Exception {
        Optional<User> user = findById(idUser);
        if (user.isEmpty()) {
            throw new Exception("Người dùng không tồn tại");
        }
        if (!Constants.ROLE_ADMIN.equals(user.get().getRole())) {
            throw new Exception("Bạn không phải là admin");
        }
    }

    @Override
    public void validateUser(User user) throws Exception {
        if (user.getUsername().length() > 50) {
            throw new Exception("User name vượt quá 50 kí tự");
        }
        // So sánh mật khẩu và xác nhận mật khẩu có giống nhau không
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Xác nhận lại mật khẩu không đúng");
        }
        if (user.getPassword().length() > 32 || user.getPassword().length() < 6) {
            throw new Exception("Mật khẩu phải lớn hơn 6 hoặc nhỏ hơn 32 kí tự");
        }
        if (user.getPhone().length() != 11) {
            throw new Exception("Số điện thoại chỉ có 10 số thôi");
        }
        if (user.getPin().length() != 8) {
            throw new Exception("Số pin chỉ có 8 số");
        }
        user.setStatus("Đang hoạt động");
        if (null == user.getRole() && user.isBuyer()) {
            user.setRole(Constants.ROLE_BUYER);
        } else {
            user.setRole(Constants.ROLE_SELLER);
        }
        if (user.getRole().equals(Constants.ROLE_ADMIN)) {
            user.setRole(Constants.ROLE_ADMIN);
        }
    }

    @Override
    public void checkBannerUser(String username) throws Exception {
        Optional<User> user = findUserByUsername(username);
        if (user.isEmpty()) {
            throw new Exception("Người dùng không tồn tại");
        } else {
            throw new Exception("Bạn đang bị cấm hãy liên hệ với admin");
        }
    }
}

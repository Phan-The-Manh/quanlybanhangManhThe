package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.constant.Constants;
import com.example.quanlybanhang.models.OrderProduct;
import com.example.quanlybanhang.models.Product;
import com.example.quanlybanhang.models.User;
import com.example.quanlybanhang.service.OrderProductService;
import com.example.quanlybanhang.service.ProductService;
import com.example.quanlybanhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private ProductService productService;

    // Tạo mới đơn hàng
    @GetMapping("/order")
    public ResponseEntity<?> update(@RequestParam(required = false) Long idOrder, @RequestParam Long idUser,
                                    @RequestParam List<Long> listIdProduct) {
        try {
            Optional<User> userOptional = userService.findById(idUser);
            if (userOptional.isEmpty()) {
                return new ResponseEntity<>("Không tìm thấy người dùng", HttpStatus.NOT_FOUND);
            }
            Set<Product> productSet = productService.findAllByIdProductIn(listIdProduct);
            Long id = 0L;
            if (null != idOrder) {
                id = idOrder;
            }
            Optional<OrderProduct> optionalOrderProduct = orderProductService.findById(id);

            if (optionalOrderProduct.isEmpty()) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setCreateAt(new Date());
                orderProduct.setUser(userOptional.get());
                orderProduct.setProductSet(productSet);
                orderProduct.setStatus(Constants.STATUS_ORDER_PENDING);
                orderProductService.save(orderProduct);
                return new ResponseEntity<>(orderProduct, HttpStatus.OK);
            }
            optionalOrderProduct.get().setEditAt(new Date());
            optionalOrderProduct.get().setProductSet(productSet);
            orderProductService.save(optionalOrderProduct.get());
            return new ResponseEntity<>(optionalOrderProduct.get(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Đổi trạng thái đơn hàng
    @GetMapping("/changeStatus")
    public ResponseEntity<?> changeStatus(@RequestParam String status, @RequestParam Long idOrderProduct,
                                          @RequestParam Long idUser) {
        try {
            Optional<OrderProduct> optionalOrderProduct = orderProductService.findById(idOrderProduct);
            if (optionalOrderProduct.isEmpty()) {
                return new ResponseEntity<>("không tìm thấy đơn hàng", HttpStatus.BAD_REQUEST);
            } else {
                if (optionalOrderProduct.get().getUser().getId().equals(idUser)) {
                    if (optionalOrderProduct.get().getStatus().equals(Constants.STATUS_ORDER_BOUGHT)) {
                        return new ResponseEntity<>("Đơn hàng này đã hoàn thành", HttpStatus.BAD_REQUEST);
                    } else {
                        optionalOrderProduct.get().setStatus(status);
                        optionalOrderProduct.get().setEditAt(new Date());
                        orderProductService.save(optionalOrderProduct.get());
                    }
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Đơn hàng này không phải của bạn", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

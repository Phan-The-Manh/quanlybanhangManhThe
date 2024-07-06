package com.example.quanlybanhang.controller;


import com.example.quanlybanhang.models.Star;
import com.example.quanlybanhang.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/stars")
public class StarController {

    @Autowired
    private StarService starService;

    // Xem tất cả đánh giá của 1 sản phẩm
    @GetMapping("/get-star")
    public ResponseEntity<?> getAllStarByProduct(@RequestParam Long idProduct) {
        try {
            List<Star> list = starService.findAllByProductId(idProduct);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Xem đánh giá của sản phẩm theo số sao
    @GetMapping("/get-star-by-type")
    public ResponseEntity<?> getAllStarByProductAndType(@RequestParam Long idProduct, @RequestParam String type) {
        try {
            List<Star> list = starService.findAllByProductIdAndType(idProduct, type);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Tạo mới đánh giá
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestParam Long idProduct, @RequestParam Long idUser, @RequestParam String type) {
        try {
            Star star = starService.initStar(idUser, idProduct, type);
            starService.save(star);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

package com.example.quanlybanhang.controller;


import com.example.quanlybanhang.dto.CommentDTO;
import com.example.quanlybanhang.models.Comment;
import com.example.quanlybanhang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Xem tất cả bình luận của 1 người dùng
    @GetMapping("/get-comment-by-user")
    public ResponseEntity<?> getAllCommentByUser(@RequestParam Long idUser) {
        try {
            List<Comment> list = commentService.findAllCommentByUserId(idUser);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Xem tất cả bình luận của 1 sản phẩm
    @GetMapping("/get-comment-by-product")
    public ResponseEntity<?> getAllCommentByProduct(@RequestParam Long idProduct) {
        try {
            List<Comment> list = commentService.findAllCommentByProductId(idProduct);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Xem tất cả bình luận của 1 sản phẩm và của 1 người dùng
    @GetMapping("/get-comment")
    public ResponseEntity<?> getAllCommentByProductAndUser(@RequestParam Long idProduct, @RequestParam Long idUer) {
        try {
            List<Comment> list = commentService.findAllCommentByProductIdAndUserId(idProduct, idUer);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Tạo mới bình luận
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CommentDTO commentDTO) {
        try {
           Comment comment = commentService.validateCommentAndInit(commentDTO);
            commentService.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Xóa bình luận
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long idComment) {
        try {
            commentService.delete(idComment);
            return new ResponseEntity<>("Đã xóa bình luận", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

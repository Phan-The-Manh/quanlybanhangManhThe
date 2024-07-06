package com.example.quanlybanhang.service.impl;

import com.example.quanlybanhang.dto.CommentDTO;
import com.example.quanlybanhang.models.Comment;
import com.example.quanlybanhang.models.Product;
import com.example.quanlybanhang.models.User;
import com.example.quanlybanhang.repository.CommentRepository;
import com.example.quanlybanhang.service.CommentService;
import com.example.quanlybanhang.service.ProductService;
import com.example.quanlybanhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Comment> findAllCommentByUserId(Long idUser) {
        return commentRepository.findCommentByUserId(idUser);
    }

    @Override
    public List<Comment> findAllCommentByProductId(Long idProduct) {
        return commentRepository.findCommentByProductId(idProduct);
    }

    @Override
    public List<Comment> findAllCommentByProductIdAndUserId(Long idProduct, Long idUser) {
        return commentRepository.findAllCommentByProductIdAndUserId(idProduct, idUser);
    }

    @Override
    public void save(Comment comment) throws Exception {
        commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (null == id) {
            throw new Exception("Không có id");
        }
        commentRepository.deleteById(id);
    }

    @Override
    public Comment validateCommentAndInit(CommentDTO commentDTO) throws Exception {
        if (null == commentDTO.getIdProduct() || null == commentDTO.getIdUser()) {
            throw new Exception("không có id người dùng hoặc id sản phẩm");
        }
        if (null == commentDTO.getContent() || "".equals(commentDTO.getContent())) {
            throw new Exception("Không có nội dung");
        }
        Optional<User> userOptional = userService.findById(commentDTO.getIdUser());
        Optional<Product> productOptional = productService.findById(commentDTO.getIdProduct());
        if (userOptional.isEmpty() || productOptional.isEmpty()) {
            throw new Exception("không tìm thấy người dùng hoặc sản phẩm");
        }
        Comment comment = new Comment();
        comment.setCreateDate(new Date());
        comment.setContent(commentDTO.getContent());
        comment.setProduct(productOptional.get());
        comment.setUser(userOptional.get());
        return comment;
    }
}

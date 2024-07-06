package com.example.quanlybanhang.service;

import com.example.quanlybanhang.dto.CommentDTO;
import com.example.quanlybanhang.models.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllCommentByUserId(Long idUser);

    List<Comment> findAllCommentByProductId(Long idProduct);

    List<Comment> findAllCommentByProductIdAndUserId(Long idProduct, Long idUser);

    void save(Comment comment) throws Exception;

    void delete(Long id) throws Exception;

    Comment validateCommentAndInit(CommentDTO commentDTO) throws Exception;

}

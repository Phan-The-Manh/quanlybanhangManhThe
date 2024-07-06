package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findCommentByUserId(Long idUser);

    @Modifying
    @Query(value = "select * from comment where id_product = :idProduct", nativeQuery = true)
    List<Comment> findCommentByProductId(Long idProduct);

    @Modifying
    @Query(value = "select * from comment where id_product = :idProduct and id_user = :idUser", nativeQuery = true)
    List<Comment> findAllCommentByProductIdAndUserId(Long idProduct, Long idUser);
}

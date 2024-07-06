package com.example.quanlybanhang.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private String content;
    private Long idUser;
    private Long idProduct;
}

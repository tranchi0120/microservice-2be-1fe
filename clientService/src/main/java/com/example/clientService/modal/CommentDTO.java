package com.example.clientService.modal;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private PostDTO post;
    private  Long user_id;
}

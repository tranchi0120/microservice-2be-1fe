package com.example.clientService.modal;

import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private Long user_id;
}

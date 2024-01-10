package com.example.postCommentService.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

}

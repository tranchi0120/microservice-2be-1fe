package com.example.postCommentService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;


    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;
}

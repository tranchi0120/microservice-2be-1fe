package com.example.postCommentService.controller;


import com.example.postCommentService.entity.Post;
import com.example.postCommentService.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/posts")
public class PostResController {
    @Autowired
    PostService postService;

    @GetMapping()
    public ResponseEntity<List<Post>> listPost() {
        List<Post> posts = postService.getAllPost();
        if (posts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(posts);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getSinglePost(@PathVariable Long id) {
        Optional<Post> post = postService.getSingPost(id);

        if (post.isPresent()) {
            return ResponseEntity.ok(post);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
    }

    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@RequestBody Post newPost) {
        try {
            Post addedPost = postService.addPost(newPost);
            return new ResponseEntity<>(addedPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add user: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Post>> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> updatedPost = postService.updatePost(id, post);

        if (updatedPost.isPresent()) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {
        boolean deleted = postService.deletePost(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

package com.example.postCommentService.controller;

import com.example.postCommentService.entity.Comment;
import com.example.postCommentService.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping()
    public ResponseEntity<List<Comment>> listComment() {
        List<Comment> comments = commentService.getAllComment();
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(comments);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comment>> getSingleComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getSingleComment(id);

        if (comment.isPresent()) {
            return ResponseEntity.ok(comment);

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody Comment newComment) {
        try {
            Comment addedComment = commentService.addComment(newComment);
            return new ResponseEntity<>(addedComment, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add user: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Comment>> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> updatedComment = commentService.updateComment(id, comment);

        if (updatedComment.isPresent()) {
            return ResponseEntity.ok(updatedComment);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable Long id) {
        boolean deleted = commentService.deleteComment(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
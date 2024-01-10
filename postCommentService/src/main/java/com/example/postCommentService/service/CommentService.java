package com.example.postCommentService.service;

import com.example.postCommentService.entity.Comment;
import com.example.postCommentService.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getSingleComment(Long id) {
        return commentRepository.findById(id);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> updateComment(Long id, Comment comment) {
        Optional<Comment> exitsCommentOption = commentRepository.findById(id);

        if (exitsCommentOption.isPresent()) {
            Comment exitstingComment = exitsCommentOption.get();
            exitstingComment.setContent(comment.getContent());

            commentRepository.save(exitstingComment);
            return Optional.of(exitstingComment);
        } else {
            return Optional.empty();
        }
    }

    public Boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

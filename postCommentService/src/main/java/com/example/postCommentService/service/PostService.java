package com.example.postCommentService.service;


import com.example.postCommentService.entity.Post;
import com.example.postCommentService.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Optional<Post> getSingPost(Long id) {
        return postRepository.findById(id);
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> updatePost(Long id, Post post) {
        Optional<Post> exitsPostOption = postRepository.findById(id);
        if (exitsPostOption.isPresent()) {
            Post exitstingPost = exitsPostOption.get();
            exitstingPost.setContent(post.getContent());
            exitstingPost.setTitle(post.getTitle());

            postRepository.save(exitstingPost);
            return Optional.of(exitstingPost);
        } else {
            return Optional.empty();
        }
    }

    public boolean deletePost(Long postId) {
        if (postRepository.existsById(postId)) {
            postRepository.deletePostAndComments(postId);
            postRepository.deleteById(postId);
            return true;
        } else {
            return false;
        }
    }

}

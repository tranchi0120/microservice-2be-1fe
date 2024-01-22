package com.example.clientService.service;

import com.example.clientService.FeignClient.ProductAPI;
import com.example.clientService.modal.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    ProductAPI productAPI;

    public List<PostDTO> getAllPost() {
        return productAPI.getPost();
    }

    public void addPost(PostDTO post) {
        PostDTO addedPost = productAPI.addPost(post);
    }

    public PostDTO getPostId(Long postId) {
        return productAPI.getPostId(postId);
    }

    public void updatePost(Long id, PostDTO updatedPost) {
        PostDTO staffUpdate = productAPI.getPostId(updatedPost.getId());
        if (staffUpdate != null && staffUpdate.getId().equals(updatedPost.getId())) {
            staffUpdate.setTitle(updatedPost.getTitle());
            staffUpdate.setContent(updatedPost.getContent());
            productAPI.updatePost(updatedPost.getId(), staffUpdate);
        }
        productAPI.updatePost(id, updatedPost);
    }

    public void deletePost(Long id) {
        productAPI.deletePost(id);
    }
}

package com.example.clientService.FeignClient;

import com.example.clientService.modal.CommentDTO;
import com.example.clientService.modal.PostDTO;
import com.example.clientService.modal.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product", url = "http://localhost:8080/api/")
public interface ProductAPI {
    @GetMapping("/users")
    List<UserDTO> getUser();

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable Long id);

    @PostMapping("/users/addUser")
    UserDTO addUser(UserDTO newUser);

    @PutMapping("/users/update/{id}")
    UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUser);

    /*post*/
    @GetMapping("/posts")
    List<PostDTO> getPost();

    @GetMapping("/posts/{id}")
    PostDTO getPostId(@PathVariable Long id);

    @PostMapping("/posts/addPost")
    PostDTO addPost(PostDTO newPost);

    @PutMapping("/posts/update/{id}")
    PostDTO updatePost(@PathVariable Long id, @RequestBody PostDTO updatedPost);

    @DeleteMapping("/posts//delete/{id}")
    void deletePost(@PathVariable Long id);


    /*comments*/

    @GetMapping("/comments")
    List<CommentDTO> getComment();

    @GetMapping("/comments/{id}")
    CommentDTO getCommentId(@PathVariable Long id);

    @PostMapping("/comments/addComment")
    CommentDTO addComment(CommentDTO newPost);

    @PutMapping("/comments/update/{id}")
    CommentDTO updateComment(@PathVariable Long id, @RequestBody CommentDTO updatedPost);

    @DeleteMapping("/comments/delete/{id}")
    void deleteComment(@PathVariable Long id);
}

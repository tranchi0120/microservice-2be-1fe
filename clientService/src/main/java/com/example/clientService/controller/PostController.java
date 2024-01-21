package com.example.clientService.controller;

import com.example.clientService.modal.PostDTO;
import com.example.clientService.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class PostController {
    @Autowired
    PostService postService;


    @GetMapping("/listPost")
    public String listPost(Model model) {
        List<PostDTO> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "post/listPost";
    }

    @GetMapping("/getPost")
    public String getPost(Model model) {
        PostDTO postForm = new PostDTO();
        model.addAttribute("postForm", postForm);
        return "post/postForm";
    }

    @PostMapping("/addPost")
    public String addPostForm( @ModelAttribute("postForm") PostDTO postForm, Model model) {
        postService.addPost(postForm);
        model.addAttribute("postForm", postForm);
        return "redirect:/product/listPost";
    }

    @GetMapping("/editPost/{id}")
    public String getPostId(@PathVariable("id") Long id, Model model) {
        PostDTO postForm = postService.getPostId(id);
        model.addAttribute("postForm", postForm);
        return "post/postForm";
    }


    @PostMapping("/updatePost/{id}")
    public String updateForm(@PathVariable Long id, @ModelAttribute("postForm") PostDTO postForm) {
        postService.updatePost(id, postForm);
        return "redirect:/product/listPost";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return "redirect:/product/listPost";
    }
}

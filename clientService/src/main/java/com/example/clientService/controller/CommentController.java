package com.example.clientService.controller;

import com.example.clientService.modal.CommentDTO;
import com.example.clientService.modal.PostDTO;
import com.example.clientService.service.CommentService;
import com.example.clientService.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;


    @GetMapping("/listComment")
    public String listComment(Model model) {
        List<CommentDTO> comments = commentService.getAllComment();
        System.out.println("comment: " +comments);
        model.addAttribute("comments", comments);
        return "comment/listComment";
    }

    @GetMapping("/getComment")
    public String getComment(Model model) {
        CommentDTO commentForm = new CommentDTO();
        List<PostDTO> postFormList = postService.getAllPost();
        model.addAttribute("commentForm", commentForm);
        model.addAttribute("postFormList", postFormList);
        return "comment/commentForm";
    }


    @PostMapping("/addComment")
    public String addCommentForm(@ModelAttribute("postForm") CommentDTO commentForm,
                                 @ModelAttribute("postFormList") PostDTO postFormList,
                                 Model model) {
        commentService.addComment(commentForm);
        model.addAttribute("commentForm", commentForm);
        model.addAttribute("postFormList", postFormList);
        return "redirect:/product/listComment";
    }

    @GetMapping("/editComment/{id}")
    public String getPostId(@PathVariable("id") Long id, Model model) {
        CommentDTO commentForm = commentService.getCommentId(id);
        model.addAttribute("commentForm", commentForm);
        return "comment/commentForm";
    }


    @PostMapping("/updateComment/{id}")
    public String updateForm(@PathVariable Long id,
                             @ModelAttribute("commentForm") CommentDTO commentForm,
                             @ModelAttribute("postFormList") PostDTO postFormList,
                             Model model) {
        model.addAttribute("postFormList", postFormList);
        commentService.updateComment(id, commentForm);
        return "redirect:/product/listComment";
    }

    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/product/listComment";
    }
}

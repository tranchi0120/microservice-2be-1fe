package com.example.clientService.service;

import com.example.clientService.FeignClient.ProductAPI;
import com.example.clientService.modal.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    ProductAPI productAPI;

    public List<CommentDTO> getAllComment() {
        return productAPI.getComment();
    }

    public void addComment(CommentDTO comment) {
        productAPI.addComment(comment);
    }

    public CommentDTO getCommentId(Long commentId) {
        return productAPI.getCommentId(commentId);
    }


    public void updateComment(Long id, CommentDTO updatedComment) {
        CommentDTO staffUpdate = productAPI.getCommentId(updatedComment.getId());
        if (staffUpdate != null && staffUpdate.getId().equals(updatedComment.getId())) {
            staffUpdate.setContent(updatedComment.getContent());
            productAPI.updateComment(updatedComment.getId(), staffUpdate);
        }
        productAPI.updateComment(id, updatedComment);
    }


    public void deleteComment(Long id){
        productAPI.deleteComment(id);
    }

}

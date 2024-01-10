package com.example.postCommentService.repository;


import com.example.postCommentService.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.post.id = ?1")
    void deletePostAndComments(Long postId);
}

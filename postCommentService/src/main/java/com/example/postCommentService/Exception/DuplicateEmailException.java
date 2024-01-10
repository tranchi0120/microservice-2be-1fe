package com.example.postCommentService.Exception;

public class DuplicateEmailException extends  RuntimeException{
    public DuplicateEmailException(String message) {
        super(message);
    }
}

package com.bloggingapp.services;

import com.bloggingapp.dtopayloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId);

    void deleteComment(Integer commentId);
}

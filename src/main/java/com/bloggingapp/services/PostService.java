package com.bloggingapp.services;

import com.bloggingapp.dtopayloads.PostDto;

import java.util.List;


public interface PostService {

    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categotyId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //getallpost

    List<PostDto> getAllPost();

    //getpostbyID

    PostDto getPostbyId(Integer postId);

    //get all post by categories
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostByUser(Integer userId);


    //search posts
// List<Post> searchPost(String keyword);


}

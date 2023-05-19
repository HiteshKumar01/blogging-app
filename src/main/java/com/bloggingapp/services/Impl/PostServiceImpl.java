package com.bloggingapp.services.Impl;

import com.bloggingapp.dtopayloads.PostDto;
import com.bloggingapp.entities.Category;
import com.bloggingapp.entities.Post;
import com.bloggingapp.entities.User;
import com.bloggingapp.exceptions.ResourceNotFoundException;
import com.bloggingapp.repositories.CategoryRepo;
import com.bloggingapp.repositories.PostRepo;
import com.bloggingapp.repositories.UserRepo;
import com.bloggingapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category id",categoryId));

        Post post=this.modelMapper.map(postDto,Post.class);
      //  post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost=this.postRepo.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }
//update
    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
      //  post.setImageName(postDto.getImageName());

        Post updatedPost=this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
        this.postRepo.delete(post);
    }

    //get all posts
    @Override
    public List<PostDto> getAllPost() {

        List<Post> allPosts= this.postRepo.findAll();
        return allPosts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    //get single post by id
    @Override
    public PostDto getPostbyId(Integer postId) {

        Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));

        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category id",categoryId));
        List<Post> posts=this.postRepo.findByCategory(cat);

        return posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
        List<Post> posts=this.postRepo.findByUser(user);

        return posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }

//    @Override
//    public List<Post> searchPost(String keyword) {
//       return null;
//    }
}

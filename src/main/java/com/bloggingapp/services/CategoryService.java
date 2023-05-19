package com.bloggingapp.services;

import com.bloggingapp.dtopayloads.CategoryDto;

import java.util.List;


public interface CategoryService {
    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //get
    CategoryDto getCategory(Integer categoryId);
    //getall
    List<CategoryDto> getCategories();
}

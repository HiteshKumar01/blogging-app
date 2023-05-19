package com.bloggingapp.controllers;

import com.bloggingapp.dtopayloads.ApiResponse;
import com.bloggingapp.dtopayloads.CategoryDto;
import com.bloggingapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId) {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!!", true), HttpStatus.OK);
    }
        //getby id
        @GetMapping("/{catId}")
        public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
            CategoryDto categoryDto=this.categoryService.getCategory(catId);
            return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);

        }
        //getall

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories(){

      List<CategoryDto> categories=this.categoryService.getCategories();
      return ResponseEntity.ok(categories);
    }
    }


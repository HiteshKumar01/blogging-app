package com.bloggingapp.repositories;

import com.bloggingapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepo extends JpaRepository<Category,Integer> {


}

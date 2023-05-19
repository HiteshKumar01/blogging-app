package com.bloggingapp.repositories;

import com.bloggingapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Integer>{
}

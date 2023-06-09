package com.bloggingapp.services.Impl;

import com.bloggingapp.dtopayloads.UserDto;
import com.bloggingapp.exceptions.ResourceNotFoundException;
import com.bloggingapp.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

import com.bloggingapp.entities.User;
import com.bloggingapp.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	
    @Override
    public UserDto createUser(UserDto userDto) {
    	User user=this.dtoToUser(userDto);
    	
    	User savedUser=this.userRepo.save(user);
    	
    	return this.userToDto(savedUser);
    	
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
    	
    	User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
    	
    	user.setName(userDto.getName());
    	user.setEmail(userDto.getEmail());
    	user.setPassword(userDto.getPassword());
    	user.setAbout(userDto.getAbout());
    	
    	User updatedUser=this.userRepo.save(user);

		return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
    	User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
    	
    	List<User> users=this.userRepo.findAll();

		return users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
    	
    	User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
    	this.userRepo.delete(user);

    }
    
    private User dtoToUser(UserDto userDto) {

		/*User user=new User();
    	user.setId(userDto.getId());
    	user.setName(userDto.getName());
    	user.setEmail(userDto.getEmail());
    	user.setAbout(userDto.getAbout());
    	user.setPassword(userDto.getPassword());*/
    	
    	return this.modelMapper.map(userDto,User.class);
    }
    
    public UserDto userToDto(User user) {
		/*UserDto userDto=new UserDto();
    	userDto.setId(user.getId());
    	userDto.setName(user.getName());
    	userDto.setEmail(user.getEmail());
    	userDto.setAbout(user.getAbout());
    	userDto.setPassword(user.getPassword());*/
    	
    	return this.modelMapper.map(user,UserDto.class);
    }
}

package com.bloggingapp.dtopayloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;

	@NotEmpty
	@Size(min=6,message = "Username must be minimum of 6 character")
	private String name;

	@Email(message = "Email address is not Valid")
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String email;

	@NotEmpty
	@Size(min=8,max=14,message = "Password must be minimum of 8 characters and maximum of 14 characters,Use Atleast one Uppercase and lowercase char,one number and one special char")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W|])(?=\\S+$).{8,}$")
	private String password;
	@NotEmpty
	private String about;
	

}

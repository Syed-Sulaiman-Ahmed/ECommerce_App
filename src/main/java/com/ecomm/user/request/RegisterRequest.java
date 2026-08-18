package com.ecomm.user.request;

import java.time.LocalDate;

import com.ecomm.user.enums.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

	private String email;

	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private LocalDate dob;
	
	private RoleType roleName;
	
}

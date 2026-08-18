package com.ecomm.user.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.dto.UserDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.entity.Role;
import com.ecomm.user.entity.User;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.UserRepository;
import com.ecomm.user.request.RegisterRequest;
import com.ecomm.user.service.ProfileService;
import com.ecomm.user.service.RoleService;
import com.ecomm.user.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RoleService rservice;
	
	@Autowired
	private ProfileService pservice;
	
	@Transactional
	@Override
	public UserDto register(RegisterRequest request) {
		
		//checking if user is existing user or not
		User exists=urepo.findByEmail(request.getEmail()).orElse(null);
		if(exists!=null) {
			throw new AppException("User already exists",HttpStatus.CONFLICT);
		}
		
		//Logic for Adding User Data
		User newUser=mapper.map(request, User.class);
		//Fetch the role
		RoleDto rdto=rservice.getRoleByRoleName(request.getRoleName());
		Role role=mapper.map(rdto, Role.class);
		newUser.setRole(role);
		newUser=urepo.save(newUser);
		
		//Adding Profile
		Profile profile=mapper.map(request, Profile.class);
		profile.setUser(newUser);
		ProfileDto pdto=pservice.addProfile(profile);
		
		UserDto dto=mapper.map(newUser, UserDto.class);
		dto.setPdto(pdto);
		dto.setRdto(rdto);
		return dto;
	}

}

package com.ecomm.user.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.entity.Role;
import com.ecomm.user.enums.RoleType;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.RoleRepository;
import com.ecomm.user.request.AddRoleRequest;
import com.ecomm.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository rrepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public RoleDto addRole(AddRoleRequest request) {
		
		Role existing=rrepo.findByRoleName(request.getRoleName()).orElse(null);
		
		if(existing!=null) {
			throw new AppException("This role already exists!", HttpStatus.CONFLICT);
		}
		
		Role newRole=mapper.map(request, Role.class);
		newRole=rrepo.save(newRole);
		
		RoleDto roleDto=mapper.map(newRole, RoleDto.class);
		
		return roleDto;
	}

	@Override
	public RoleDto getRoleByRoleName(RoleType roleName) {
		Role role=rrepo.findByRoleName(roleName).orElseThrow(()->new AppException("Role Not Found!", HttpStatus.NOT_FOUND));
		return mapper.map(role, RoleDto.class);
	}

}

package com.ecomm.user.service;

import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.enums.RoleType;
import com.ecomm.user.request.AddRoleRequest;

public interface RoleService {

	RoleDto addRole(AddRoleRequest request);
	
	RoleDto getRoleByRoleName(RoleType roleName);
}

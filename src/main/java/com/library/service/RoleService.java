package com.library.service;

import com.library.enums.UserRole;
import com.library.model.Role;

public interface RoleService {
	Role findByName(UserRole role);
}

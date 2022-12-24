package com.library.service.impl;

import org.springframework.stereotype.Service;

import com.library.enums.UserRole;
import com.library.model.Role;
import com.library.repository.RoleRepository;
import com.library.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

	private final RoleRepository roleRepository;
	
	@Override
	public Role findByName(UserRole urole) {
		return this.roleRepository.findByName(urole).orElseThrow(null);
	}
	
}

package com.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.entity.UserDTO;
import com.library.enums.UserRole;
import com.library.model.Role;
import com.library.model.User;
import com.library.repository.UserRepository;
import com.library.service.RoleService;
import com.library.service.UserService;
import com.library.util.ModelUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final RoleService roleService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelUtil modelUtil;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User user = this.modelUtil.map(userDTO, User.class);
		
		Set<String> strRoles = userDTO.getRoles();
		List<Role> roles = new ArrayList<>();
		
		if(strRoles == null) {
			Role userRole = this.roleService.findByName(UserRole.ROLE_CUSTOMER);
			roles.add(userRole);
		}else {
			strRoles.forEach(role -> {
				UserRole ur = UserRole.valueOf(role);
				Role relatedRole = this.roleService.findByName(ur);
				roles.add(relatedRole);
			});
		}
		
		user.setRoles(roles);
		return this.modelUtil.map(this.userRepository.save(user), UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		
		return this.modelUtil.map(users, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Integer id) {
		User userDb = this.userRepository.findById(id).orElseThrow(null);
		
		return this.modelUtil.map(userDb, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(Integer id, UserDTO userDTO) {
		User userDb = this.userRepository.findById(id).orElseThrow(null);
		User user = this.modelUtil.map(userDTO, User.class);
		boolean changedUser = mergeUser(userDb, user);
		
		if(changedUser) {
			this.userRepository.save(userDb);
			return this.modelUtil.map(userDb, UserDTO.class);
		}
		
		return userDTO;
	}

	@Override
	public String deleteUser(Integer id) {
		User user = this.userRepository.findById(id).orElseThrow();
		List<Role> roles = user.getRoles();
		
		roles.clear();
		
		this.userRepository.deleteById(id);
		return "Deleted!";
	}
	
	private static boolean mergeUser(User userDb, User user) {
		boolean changedUser = false;
		if(isEquals(userDb.getEmail(), user.getEmail())) {
			userDb.setEmail(user.getEmail());
			changedUser = true;
		}
		if(isEquals(userDb.getName(), user.getName())) {
			userDb.setName(user.getName());
			changedUser = true;
		}
		if(isEquals(userDb.getUsername(), user.getUsername())) {
			userDb.setUsername(user.getUsername());
		}
		
		return changedUser;
	}
	
	private static boolean isEquals(Object src, Object dest) {
		return src.equals(dest);
	}

}

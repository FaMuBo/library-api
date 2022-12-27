package com.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.library.entity.UserResponseDTO;
import com.library.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final RoleService roleService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelUtil modelUtil;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@Override
	public UserResponseDTO login(UserDTO userDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		return UserResponseDTO.builder()
				.token("Bearer " + jwt)
				.build();
	}

	@Override
	public UserDTO register(UserDTO userDTO) {
		if(Boolean.TRUE.equals(userRepository.existsByUsername(userDTO.getUsername()))){
			log.error("User already exist!");
			return null;
		}

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
		UserDTO map = this.modelUtil.map(this.userRepository.save(user), UserDTO.class);
		log.info("User created for: " + map);
		return map;
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

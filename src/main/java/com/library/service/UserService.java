package com.library.service;

import java.util.List;

import com.library.entity.UserDTO;
import com.library.entity.UserResponseDTO;

public interface UserService {
	UserResponseDTO login(UserDTO userDTO);

	UserDTO register(UserDTO userDTO);
	
	List<UserDTO> getAllUsers();
	
	UserDTO getUserById(Integer id);
	
	UserDTO updateUser(Integer id, UserDTO userDTO);
	
	String deleteUser(Integer id);
}

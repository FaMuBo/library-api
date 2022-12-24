package com.library.service;

import java.util.List;

import com.library.entity.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO userDTO);
	
	List<UserDTO> getAllUsers();
	
	UserDTO getUserById(Integer id);
	
	UserDTO updateUser(Integer id, UserDTO userDTO);
	
	String deleteUser(Integer id);
}

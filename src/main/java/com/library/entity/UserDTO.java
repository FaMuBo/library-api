package com.library.entity;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {
	private String name;
	
	private LocalDate birthDate;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private Set<String> roles;
}

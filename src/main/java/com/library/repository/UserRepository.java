package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findUserByUsername(String username);

	Boolean existsByUsername(String username);

}

package com.library.service;

import com.library.entity.AuthorRequestDTO;
import com.library.entity.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
	AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO);
	
	List<AuthorResponseDTO> getAllAuthors();

	AuthorResponseDTO getAuthorById(Integer id);

	AuthorResponseDTO updateAuthor(Integer id, AuthorRequestDTO authorRequestDTO);
	
	String deleteAuthor(Integer id);
}

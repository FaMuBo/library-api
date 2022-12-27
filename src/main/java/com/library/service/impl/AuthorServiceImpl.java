package com.library.service.impl;

import com.library.entity.AuthorRequestDTO;
import com.library.entity.AuthorResponseDTO;
import com.library.model.Author;
import com.library.repository.AuthorRepository;
import com.library.service.AuthorService;
import com.library.util.ModelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

	private final AuthorRepository authorRepository;
	private final ModelUtil modelUtil;

	@Override
	public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
		Author author = this.modelUtil.map(authorRequestDTO, Author.class);
		return this.modelUtil.map(this.authorRepository.save(author), AuthorResponseDTO.class);
	}

	@Override
	public List<AuthorResponseDTO> getAllAuthors() {
		return this.modelUtil.map(this.authorRepository.findAll(), AuthorResponseDTO.class);
	}

	@Override
	public AuthorResponseDTO getAuthorById(Integer id) {
		return this.modelUtil.map(this.authorRepository.findById(id), AuthorResponseDTO.class);
	}

	@Override
	public AuthorResponseDTO updateAuthor(Integer id, AuthorRequestDTO authorRequestDTO) {
		Author author = this.authorRepository.findById(id).orElseThrow(null);
	
		if(author != null) {
			Author updatedAuthor = this.modelUtil.map(authorRequestDTO, Author.class);
			updatedAuthor.setId(author.getId());
			return this.modelUtil.map(updatedAuthor, AuthorResponseDTO.class);
		}
		
		return null;
	}

	@Override
	public String deleteAuthor(Integer id) {
		this.authorRepository.deleteById(id);
		return "DELETED!";
	}

}

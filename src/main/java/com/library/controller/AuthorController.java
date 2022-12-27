package com.library.controller;

import com.library.entity.AuthorRequestDTO;
import com.library.entity.AuthorResponseDTO;
import com.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
	private final AuthorService authorService;
	
	@PostMapping
	public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(this.authorService.createAuthor(authorRequestDTO));
	}
	
	@GetMapping
	public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors(){
		return ResponseEntity.ok(this.authorService.getAllAuthors());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(this.authorService.getAuthorById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable("id") Integer id, @RequestBody AuthorRequestDTO authorRequestDTO){
		return ResponseEntity.ok(this.authorService.updateAuthor(id, authorRequestDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable("id") Integer id){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.authorService.deleteAuthor(id));
	}
}

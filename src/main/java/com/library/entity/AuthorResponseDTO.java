package com.library.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AuthorResponseDTO {
	private Integer id;
	private String name;
	private String country;
	private LocalDate birthDate;
    private String photo;
	private List<BookRequestDTO> books;
}

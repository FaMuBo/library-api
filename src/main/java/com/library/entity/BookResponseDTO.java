package com.library.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponseDTO {
	private Integer id;
	private String name;
    private LocalDate releaseDate;
    private Integer pageCount;
    private String description;
    private String language;
    private String publisher;
    private AuthorRequestDTO author;
    private String photo;
}

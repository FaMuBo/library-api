package com.library.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorRequestDTO {
	private Integer id;
	private String name;
	private String country;
	private LocalDate birthDate;
    private String photo;
}

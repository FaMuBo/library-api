package com.library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequestDTO {
	private Integer id;
	private String name;
    private LocalDate releaseDate;
    private Integer pageCount;
    private String description;
    private String language;
    private String publisher;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer authorId;
}

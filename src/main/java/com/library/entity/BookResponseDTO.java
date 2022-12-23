package com.library.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponseDTO {
    private String name;
    private String author;
    private LocalDate releaseDate;
    private Integer pageCount;
    private String description;
    private String language;
    private String publisher;
}

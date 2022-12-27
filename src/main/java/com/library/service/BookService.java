package com.library.service;

import java.util.List;

import com.library.entity.BookRequestDTO;
import com.library.entity.BookResponseDTO;

public interface BookService {
	BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO  getBookById(Integer id);
    BookResponseDTO updateBook(Integer id, BookRequestDTO bookRequestDTO);
    String deleteBook(Integer id);
}

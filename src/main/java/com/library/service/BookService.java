package com.library.service;

import java.util.List;

import com.library.entity.BookDTO;

public interface BookService {
	BookDTO createBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Integer id);
    BookDTO updateBook(Integer id, BookDTO bookDTO);
    String deleteBook(Integer id);
}

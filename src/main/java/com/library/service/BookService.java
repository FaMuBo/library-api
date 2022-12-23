package com.library.service;

import com.library.entity.BookRequestDTO;
import com.library.entity.BookResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Integer id);
    BookResponseDTO updateBook(Integer id, BookRequestDTO bookRequestDTO);
    String deleteBook(Integer id);
}

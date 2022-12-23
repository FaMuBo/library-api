package com.library.service.impl;

import com.library.entity.BookRequestDTO;
import com.library.entity.BookResponseDTO;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import com.library.util.ModelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelUtil modelUtil;

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = this.bookRepository.save(modelUtil.map(bookRequestDTO, Book.class));
        return modelUtil.map(book, BookResponseDTO.class);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return modelUtil.map(books, BookResponseDTO.class);
    }

    @Override
    public BookResponseDTO getBookById(Integer id) {
        Optional<Book> book = this.bookRepository.findById(id);

        return book.map(value -> modelUtil.map(value, BookResponseDTO.class)).orElse(null);
    }

    @Override
    public BookResponseDTO updateBook(Integer id, BookRequestDTO bookRequestDTO) {
        Optional<Book> bookDb = this.bookRepository.findById(id);
        Book book = modelUtil.map(bookRequestDTO, Book.class);
        if(bookDb.isPresent()){
            book.setId(bookDb.get().getId());
            this.bookRepository.save(book);
            return modelUtil.map(book, BookResponseDTO.class);
        }
        return null;
    }

    @Override
    public String deleteBook(Integer id) {
        this.bookRepository.deleteById(id);
        return "Deleted!";
    }
}

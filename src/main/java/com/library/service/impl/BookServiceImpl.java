package com.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.entity.BookDTO;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import com.library.util.ModelUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelUtil modelUtil;

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = this.bookRepository.save(modelUtil.map(bookDTO, Book.class));
        return modelUtil.map(book, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = this.bookRepository.findAll();
        return modelUtil.map(books, BookDTO.class);
    }

    @Override
    public BookDTO getBookById(Integer id) {
        Optional<Book> book = this.bookRepository.findById(id);

        return book.map(value -> modelUtil.map(value, BookDTO.class)).orElse(null);
    }

    @Override
    public BookDTO updateBook(Integer id, BookDTO bookDTO) {
        Optional<Book> bookDb = this.bookRepository.findById(id);
        Book book = modelUtil.map(bookDTO, Book.class);
        if(bookDb.isPresent()){
            book.setId(bookDb.get().getId());
            this.bookRepository.save(book);
            return modelUtil.map(book, BookDTO.class);
        }
        return null;
    }

    @Override
    public String deleteBook(Integer id) {
        this.bookRepository.deleteById(id);
        return "Deleted!";
    }
}

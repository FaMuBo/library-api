package com.library.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.library.entity.BookRequestDTO;
import com.library.entity.BookResponseDTO;
import com.library.model.Author;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.AuthorService;
import com.library.service.BookService;
import com.library.util.ModelUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final ModelUtil modelUtil;

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Author author = this.modelUtil.map(this.authorService.getAuthorById(bookRequestDTO.getAuthorId()), Author.class);
    	Book book = modelUtil.map(bookRequestDTO, Book.class);

    	book.setAuthor(author);
    	try {
            Book createdBook = this.bookRepository.save(book);
            return modelUtil.map(createdBook, BookResponseDTO.class);
		} catch (Exception e) {
			log.error("ERROR: {} AUTHOR: {} BOOK: {}", e.getMessage(), author, book);
			return null;
		}
		
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
    public BookResponseDTO updateBook(Integer id, BookRequestDTO bookDTO) {
        Optional<Book> bookDb = this.bookRepository.findById(id);
        Book book = modelUtil.map(bookDTO, Book.class);
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

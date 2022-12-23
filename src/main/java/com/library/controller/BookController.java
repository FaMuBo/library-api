package com.library.controller;

import com.library.entity.BookRequestDTO;
import com.library.entity.BookResponseDTO;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO){
        return ResponseEntity.ok(this.bookService.createBook(bookRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(this.bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable("id") Integer id, @RequestBody BookRequestDTO bookRequestDTO){
        return ResponseEntity.ok(this.bookService.updateBook(id, bookRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.bookService.deleteBook(id));
    }

}

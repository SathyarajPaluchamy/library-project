package com.project.library.controller;

import com.project.library.service.BookService;
import com.project.library.dto.BookRequest;
import com.project.library.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List; 
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/books")
public class BookController {

 private final BookService bookService;
  public BookController(BookService service)
  { 
    this.bookService = service;
 }

 @GetMapping
 public ResponseEntity<List<Book>> getAllbooks(){
return ResponseEntity.ok(bookService.listAllBook());
 }

 @GetMapping("/{id}")
 public ResponseEntity<Book> getBookById(@PathVariable Long id){
    return ResponseEntity.ok(bookService.getByBookId(id));
 }

 @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BookRequest req) {
        Book b = bookService.register(req.getIsbnNo(), req.getTitle(), req.getAuthor());
        return ResponseEntity.ok(b);
    }

    
}

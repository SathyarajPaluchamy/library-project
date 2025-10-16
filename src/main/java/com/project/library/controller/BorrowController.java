package com.project.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.library.dto.BorrowerRequest;
import com.project.library.service.BorrowService;
import com.project.library.model.Book;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }
    
    @PostMapping("/getBook")
    public ResponseEntity<?> borrowBook(@Valid @RequestBody BorrowerRequest entity) {
        Book borrow = borrowService.borrowBook(entity.getBookId(), entity.getBorrowerId());
        return ResponseEntity.ok(borrow);
    }

    @PostMapping("/returnBook")
    public ResponseEntity<?> returnBook(@Valid @RequestBody BorrowerRequest entity) {
        Book borrow = borrowService.returnBook(entity.getBookId(), entity.getBorrowerId());
        return ResponseEntity.ok(borrow);
    }
    
}

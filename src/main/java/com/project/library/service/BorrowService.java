package com.project.library.service;


import org.springframework.stereotype.Service;

import com.project.library.model.Book;
import com.project.library.model.Borrower;

import jakarta.transaction.Transactional;

@Service
public class BorrowService {
    private final BookService bookService;
    private final BorrowerService borrowerService;

    public BorrowService(BookService bookService, BorrowerService borrowerService) {
        this.bookService = bookService;
        this.borrowerService = borrowerService;
    }

    @Transactional
    public Book borrowBook(Long id, Long bookId) {
        Borrower borrower = borrowerService.getById(id);
        Book book = bookService.getByBookId(bookId);

        if(!book.isAvailable()) {
            throw new IllegalStateException("Book is already borrowed.");
        }

        book.setAvailable(false);
        book.setBorrowedBy(borrower);

        return bookService.save(book);
    }

    @Transactional
    public Book returnBook (Long id, Long BookId) {
        Borrower borrower = borrowerService.getById(id);
        Book book = bookService.getByBookId(BookId);

        if(book.isAvailable()){
            throw new IllegalStateException("Book is already available");
        }

        if(book.getBorrowedBy() == null || !book.getBorrowedBy().getId().equals(borrower.getId())) {
            throw new IllegalStateException("The book is not borrowed by this user!");
        }
        book.setAvailable(true);
        book.setBorrowedBy(null);

        return bookService.save(book);
    }


    
}

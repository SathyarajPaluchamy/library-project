package com.project.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.library.model.Book;
import com.project.library.model.Borrower;

public class BorrowServiceTest {
   @Mock
   private BookService bookService;

   @Mock
   private BorrowerService borrowerService;

   @InjectMocks
   private BorrowService borrowService;

   private Book book;
   private Borrower borrower;

     @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        borrower = new Borrower();
        borrower.setId(1L);
        borrower.setName("Alice");

        book = new Book();
        book.setBookId(10L);
        book.setTitle("Java Programming");
        book.setAvailable(true);
    }

    // Test borrowing a book successfully
    @Test
    public void testBorrowBook_Success() {
        when(borrowerService.getById(1L)).thenReturn(borrower);
        when(bookService.getByBookId(10L)).thenReturn(book);
        when(bookService.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book borrowedBook = borrowService.borrowBook(1L, 10L);

        assertFalse(borrowedBook.isAvailable());
        assertEquals(borrower, borrowedBook.getBorrowedBy());
        verify(bookService, times(1)).save(book);
    }

    // Test borrowing a book that is already borrowed
    @Test
    public void testBorrowBook_AlreadyBorrowed() {
        book.setAvailable(false);
        when(borrowerService.getById(1L)).thenReturn(borrower);
        when(bookService.getByBookId(10L)).thenReturn(book);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            borrowService.borrowBook(1L, 10L);
        });

        assertEquals("Book is already borrowed.", ex.getMessage());
        verify(bookService, never()).save(any(Book.class));
    }

    // Test returning a book successfully
    @Test
    public void testReturnBook_Success() {
        book.setAvailable(false);
        book.setBorrowedBy(borrower);

        when(borrowerService.getById(1L)).thenReturn(borrower);
        when(bookService.getByBookId(10L)).thenReturn(book);
        when(bookService.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book returnedBook = borrowService.returnBook(1L, 10L);

        assertTrue(returnedBook.isAvailable());
        assertNull(returnedBook.getBorrowedBy());
        verify(bookService, times(1)).save(book);
    }

    @Test
    public void testReturnBook_AlreadyAvailable() {
        book.setAvailable(true);
        when(borrowerService.getById(1L)).thenReturn(borrower);
        when(bookService.getByBookId(10L)).thenReturn(book);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            borrowService.returnBook(1L, 10L);
        });

        assertEquals("Book is already available", ex.getMessage());
    }

    // Test returning a book borrowed by another user
    @Test
    public void testReturnBook_BorrowedByAnotherUser() {
        Borrower anotherBorrower = new Borrower();
        anotherBorrower.setId(2L);
        anotherBorrower.setName("Bob");

        book.setAvailable(false);
        book.setBorrowedBy(anotherBorrower);

        when(borrowerService.getById(1L)).thenReturn(borrower);
        when(bookService.getByBookId(10L)).thenReturn(book);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            borrowService.returnBook(1L, 10L);
        });

        assertEquals("The book is not borrowed by this user!", ex.getMessage());
        verify(bookService, never()).save(any(Book.class));
    }

    
}

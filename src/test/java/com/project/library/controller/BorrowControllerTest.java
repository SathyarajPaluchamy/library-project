package com.project.library.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.project.library.dto.BorrowerRequest;
import com.project.library.model.Book;
import com.project.library.service.BorrowService;

public class BorrowControllerTest {

    @Mock
    private BorrowService borrowService;

    @InjectMocks
    private BorrowController borrowController;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        book = new Book();
        book.setBookId(1L);
        book.setTitle("The Alchemist");
        book.setAuthor("Paulo Coelho");
        book.setAvailable(false);
    }

    @Test
    void testBorrowBook() {
        BorrowerRequest request = new BorrowerRequest();
        request.setBookId(1L);
        request.setBorrowerId(2L);

        when(borrowService.borrowBook(1L, 2L)).thenReturn(book);

        ResponseEntity<?> response = borrowController.borrowBook(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody() instanceof Book);

        Book result = (Book) response.getBody();
        assertEquals("The Alchemist", result.getTitle());

        verify(borrowService, times(1)).borrowBook(1L, 2L);
    }

    @Test
    void testReturnBook() {
        BorrowerRequest request = new BorrowerRequest();
        request.setBookId(1L);
        request.setBorrowerId(2L);

        when(borrowService.returnBook(1L, 2L)).thenReturn(book);

        ResponseEntity<?> response = borrowController.returnBook(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody() instanceof Book);

        Book result = (Book) response.getBody();
        assertEquals("The Alchemist", result.getTitle());

        verify(borrowService, times(1)).returnBook(1L, 2L);
    }
    
}

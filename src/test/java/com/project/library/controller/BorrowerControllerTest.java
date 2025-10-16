package com.project.library.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.project.library.dto.BorrowerCreateRequest;
import com.project.library.model.Borrower;
import com.project.library.service.BorrowerService;

public class BorrowerControllerTest {

    @Mock
    private BorrowerService borrowerService;

    @InjectMocks
    private BorrowerController borrowerController;

    private Borrower borrower;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        borrower = new Borrower();
        borrower.setId(1L);
        borrower.setName("John Doe");
        borrower.setEmail("john@example.com");
    }

    @Test
    void testCreateBorrower() {
        // Arrange
        BorrowerCreateRequest request = new BorrowerCreateRequest();
        request.setName("John Doe");
        request.setEmail("john@example.com");

        when(borrowerService.create(eq("John Doe"), eq("john@example.com"))).thenReturn(borrower);

        // Act
        ResponseEntity<?> response = borrowerController.create(request);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody() instanceof Borrower);

        Borrower result = (Borrower) response.getBody();
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());

        verify(borrowerService, times(1)).create(eq("John Doe"), eq("john@example.com"));
    }

    @Test
    void testGetBorrowerWithId() {
        // Arrange
        when(borrowerService.getById(1L)).thenReturn(borrower);

        // Act
        ResponseEntity<?> response = borrowerController.getBorrowerWithId(1L);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        Borrower result = (Borrower) response.getBody();
        assertEquals("John Doe", result.getName());
        verify(borrowerService, times(1)).getById(1L);
    }
    
}

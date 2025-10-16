package com.project.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.library.model.Borrower;
import com.project.library.repository.BorrowerRepository;

public class BorrowerServiceTest {

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private BorrowerService borrowerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBorrower() {
        
        Borrower borrower = new Borrower();
        borrower.setName("John");
        borrower.setEmail("John@testmail.com");

        when(borrowerRepository.save(any(Borrower.class))).thenReturn(borrower);

        Borrower result = borrowerService.create("John", "John@testmail.com");

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(borrowerRepository, times(1)).save(argThat(saved ->
        saved.getName().equals("John") &&
        saved.getEmail().equals("John@testmail.com")));

    }
    
}

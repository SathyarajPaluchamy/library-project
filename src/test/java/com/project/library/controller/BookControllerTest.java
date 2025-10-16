package com.project.library.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.project.library.model.Book;
import com.project.library.service.BookService;

import static org.assertj.core.api.Assertions.assertThat;

public class BookControllerTest {

    @Test
    public void testGetBookList() {
        BookService mockService = mock(BookService.class);
        when(mockService.listAllBook()).thenReturn(List.of(new Book("999", "Spring Boot", "John")));

        BookController controller = new BookController(mockService);

        ResponseEntity<List<Book>> response = controller.getAllbooks();

        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).getTitle()).isEqualTo("Spring Boot");
        verify(mockService, times(1)).listAllBook();
    }
    
}

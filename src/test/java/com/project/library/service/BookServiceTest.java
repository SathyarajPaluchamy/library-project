package com.project.library.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.project.library.model.Book;
import com.project.library.repository.BookRepository;
import static org.assertj.core.api.Assertions.assertThat;

public class BookServiceTest {
    @Test
    public void testListBooks() {
        BookRepository mockBook = Mockito.mock(BookRepository.class);
        Mockito.when(mockBook.findAll()).thenReturn(List.of(new Book("12648","Sample Title","Sample Author")));

        BookService bookService = new BookService(mockBook);
        List<Book> listBooks = bookService.listAllBook();

        assertThat(listBooks).hasSize(1);
        assertThat(listBooks.get(0).getTitle()).isEqualTo("Sample Title");
    }
    
}

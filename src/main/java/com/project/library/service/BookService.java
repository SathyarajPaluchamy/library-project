package com.project.library.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.project.library.model.Book;
import com.project.library.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository repo){
        this.bookRepository =repo;
    }

    public List<Book> listAllBook(){
        return bookRepository.findAll();

    }

    public Book getByBookId(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));

    }

    public Book save(Book book){
    return  bookRepository.save(book);

    }

    public Book register(String isbnNo, String title, String author) {
        List<Book> existing = bookRepository.findByIsbnNo(isbnNo);
        if (!existing.isEmpty()) {
            Book book = existing.get(0);
            if (!book.getTitle().equals(title) || !book.getAuthor().equals(author)) {
                throw new IllegalArgumentException("ISBN conflict: existing ISBN maps to different title/author");
            }
        }
        Book b = new Book(isbnNo, title, author);
        return bookRepository.save(b);
    }
}

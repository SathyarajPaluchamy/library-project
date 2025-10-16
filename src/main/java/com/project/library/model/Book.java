package com.project.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long bookId;
    private String isbnNo;
    private String title;
    private String author;

    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY)
    private Borrower borrowedBy;

    public Long getBookId(){
        return bookId;
    }

    public void setBookId(Long bookId)
    {
     this.bookId = bookId;
    }  
    
     public String getIsbnNo(){
        return isbnNo;
    }

    public void setIsbnNo(String isbnNo)
    {
     this.isbnNo = isbnNo;
    } 

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public boolean isAvailable(){
        return available;
    }
    public void setAvailable(boolean available){
        this.available = available;
    }
    public void setBorrowedBy(Borrower borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
    public Borrower getBorrowedBy() {
        return borrowedBy;
    }


    public Book()
    {

    }

    public Book(String isbnNo, String title, String author){
        this.isbnNo = isbnNo;
        this.title = title;
        this.author = author;
        this.available = true;
    }


    
}

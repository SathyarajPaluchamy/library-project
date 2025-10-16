package com.project.library.dto;
import jakarta.validation.constraints.NotBlank;

public class BookRequest {
    
     @NotBlank
    private String isbnNo;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    public String getIsbnNo() {
        return isbnNo;
    }

    public void setIsbnNo(String isbnNo) {
        this.isbnNo = isbnNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}

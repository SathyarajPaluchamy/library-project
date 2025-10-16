package com.project.library.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.library.dto.BorrowerCreateRequest;
import com.project.library.model.Borrower;
import com.project.library.service.BorrowerService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/borrower")
public class BorrowerController {

    public BorrowerService borrowerService;
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody BorrowerCreateRequest request) {
        
        Borrower borrower = borrowerService.create(request.getName(), request.getEmail());
        
        return ResponseEntity.ok(borrower);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBorrowerWithId(@PathVariable Long id) {
        return ResponseEntity.ok(borrowerService.getById(id));
    }
    
    


    
}

package com.project.library.service;


import java.util.Optional;

import org.springframework.stereotype.Service;
import com.project.library.model.Borrower;

import com.project.library.repository.BorrowerRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Transactional
    public Borrower create(String name, String email) {
        Optional<Borrower> existingUSer = borrowerRepository.findByEmail(email);
        if (existingUSer.isPresent()) {
            throw new IllegalArgumentException("User already exists!");
        }

        Borrower borrower = new Borrower(name, email);

        return borrowerRepository.save(borrower);
    }

    public Borrower getById(Long id) {
        return borrowerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Borrower not found."));
    }
    
}

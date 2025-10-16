package com.project.library.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.library.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower,Long> {
    Optional<Borrower> findByEmail(String email);
    
}

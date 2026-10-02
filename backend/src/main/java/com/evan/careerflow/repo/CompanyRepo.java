package com.evan.careerflow.repo;

import com.evan.careerflow.models.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
    Optional<Company> findByOwnerEmail(String email);
    boolean existsByOwnerEmail(String email);

    @Query("""
            SELECT c FROM Company c
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.industry) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.location) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    Page<Company> searchCompanies(String keyword, Pageable pageable);
}
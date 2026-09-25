package com.evan.careerflow.repo;

import com.evan.careerflow.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {
    Optional<Company> findByOwnerEmail(String email);
    boolean existsByOwnerEmail(String email);
}
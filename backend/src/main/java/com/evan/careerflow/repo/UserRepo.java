package com.evan.careerflow.repo;

import com.evan.careerflow.models.Role;
import com.evan.careerflow.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    // Prepares us for Dashboard Stats
    long countByRole(Role role);

    // Fetch only candidates for the general list
    Page<User> findByRole(Role role, Pageable pageable);

    @Query("""
            SELECT u FROM User u
            WHERE u.role = :role
            AND (LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(u.headline) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(u.location) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(u.bio) LIKE LOWER(CONCAT('%', :keyword, '%')))
            """)
    Page<User> searchByRoleAndKeyword(Role role, String keyword, Pageable pageable);

}
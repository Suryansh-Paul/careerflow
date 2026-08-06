package com.evan.careerflow.repo;


import com.evan.careerflow.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface JobRepo extends JpaRepository<Job, Integer> {


    @Query("""
            SELECT j FROM Job j
            WHERE LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(j.location) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<Job> searchJobs(String keyword);


}
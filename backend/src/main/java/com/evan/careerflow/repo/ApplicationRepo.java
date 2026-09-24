package com.evan.careerflow.repo;

import com.evan.careerflow.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Integer> {
    List<Application> findByCandidateEmail(String email);
    List<Application> findByJobId(Integer jobId);
}
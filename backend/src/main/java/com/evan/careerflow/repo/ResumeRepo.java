package com.evan.careerflow.repo;


import com.evan.careerflow.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ResumeRepo extends JpaRepository<Resume, Integer> {


}
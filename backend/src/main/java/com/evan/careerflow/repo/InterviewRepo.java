package com.evan.careerflow.repo;


import com.evan.careerflow.models.Interview;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InterviewRepo extends JpaRepository<Interview,  Integer> {


}
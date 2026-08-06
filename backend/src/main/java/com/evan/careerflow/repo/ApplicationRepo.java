package com.evan.careerflow.repo;


import com.evan.careerflow.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicationRepo extends JpaRepository<Application,  Integer> {


}
package com.evan.careerflow.repo;


import com.evan.careerflow.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepo extends JpaRepository<Company,  Integer> {


}
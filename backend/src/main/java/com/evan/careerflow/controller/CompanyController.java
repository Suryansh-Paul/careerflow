package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.CompanyRequest;
import com.evan.careerflow.dtos.CompanyResponse;
import com.evan.careerflow.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(
            CompanyService companyService) {

        this.companyService = companyService;
    }


    // GET all companies
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResponse>>
    getAllCompanies() {

        return new ResponseEntity<>(
                companyService.getAllCompanies(),
                HttpStatus.OK
        );
    }


    // GET company by ID
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyResponse>
    getCompanyById(
            @PathVariable int id) {

        CompanyResponse company =
                companyService.getCompanyById(id);

        if (company != null) {

            return new ResponseEntity<>(
                    company,
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }


    // POST company
    @PostMapping("/company")
    public ResponseEntity<?> createCompany(
            @RequestBody CompanyRequest request) {

        try {

            CompanyResponse savedCompany =
                    companyService.createCompany(request);

            return new ResponseEntity<>(
                    savedCompany,
                    HttpStatus.CREATED
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    // PUT company
    @PutMapping("/company/{id}")
    public ResponseEntity<?> updateCompany(
            @PathVariable int id,
            @RequestBody CompanyRequest request) {

        try {

            CompanyResponse updatedCompany =
                    companyService.updateCompany(
                            id,
                            request
                    );

            if (updatedCompany != null) {

                return new ResponseEntity<>(
                        updatedCompany,
                        HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    "Company not found",
                    HttpStatus.NOT_FOUND
            );

        } catch (Exception e) {

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    // DELETE company
    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable int id) {

        boolean deleted =
                companyService.deleteCompany(id);

        if (deleted) {

            return new ResponseEntity<>(
                    "Company deleted successfully",
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                "Company not found",
                HttpStatus.NOT_FOUND
        );
    }
}
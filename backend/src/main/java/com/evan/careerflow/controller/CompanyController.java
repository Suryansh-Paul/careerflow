package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.CompanyRequest;
import com.evan.careerflow.dtos.CompanyResponse;
import com.evan.careerflow.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Public / Open to all authenticated users
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        return new ResponseEntity<>(
                companyService.getAllCompanies(),
                HttpStatus.OK
        );
    }

    // Public / Open to all authenticated users
    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable int id) {
        return new ResponseEntity<>(
                companyService.getCompanyById(id),
                HttpStatus.OK
        );
    }

    // Employer retrieves their own company profile
    @PreAuthorize("hasRole('EMPLOYER')")
    @GetMapping("/company/me")
    public ResponseEntity<CompanyResponse> getMyCompany(Principal principal) {
        return new ResponseEntity<>(
                companyService.getMyCompany(principal.getName()),
                HttpStatus.OK
        );
    }

    // Only EMPLOYER can create a company; owner bound from JWT token
    @PreAuthorize("hasRole('EMPLOYER')")
    @PostMapping("/company")
    public ResponseEntity<CompanyResponse> createCompany(
            @Valid @RequestBody CompanyRequest request,
            Principal principal
    ) {
        CompanyResponse savedCompany = companyService.createCompany(request, principal.getName());
        return new ResponseEntity<>(
                savedCompany,
                HttpStatus.CREATED
        );
    }

    // Only owning EMPLOYER can update company details
    @PreAuthorize("hasRole('EMPLOYER')")
    @PutMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable int id,
            @Valid @RequestBody CompanyRequest request,
            Principal principal
    ) {
        CompanyResponse updatedCompany = companyService.updateCompany(id, request, principal.getName());
        return new ResponseEntity<>(
                updatedCompany,
                HttpStatus.OK
        );
    }

    // Only owning EMPLOYER can delete company
    @PreAuthorize("hasRole('EMPLOYER')")
    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable int id,
            Principal principal
    ) {
        companyService.deleteCompany(id, principal.getName());
        return new ResponseEntity<>(
                "Company deleted successfully",
                HttpStatus.OK
        );
    }
}
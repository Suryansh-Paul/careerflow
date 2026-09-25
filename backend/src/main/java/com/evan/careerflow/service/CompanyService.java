package com.evan.careerflow.service;

import com.evan.careerflow.dtos.CompanyRequest;
import com.evan.careerflow.dtos.CompanyResponse;
import com.evan.careerflow.exceptionhandling.ResourceNotFoundException;
import com.evan.careerflow.models.Company;
import com.evan.careerflow.models.User;
import com.evan.careerflow.repo.CompanyRepo;
import com.evan.careerflow.repo.UserRepo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;

    public CompanyService(CompanyRepo companyRepo, UserRepo userRepo) {
        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public CompanyResponse getCompanyById(int id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        return convertToResponse(company);
    }

    public CompanyResponse getMyCompany(String employerEmail) {
        Company company = companyRepo.findByOwnerEmail(employerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("No company registered for user: " + employerEmail));

        return convertToResponse(company);
    }

    public CompanyResponse createCompany(CompanyRequest request, String employerEmail) {
        if (companyRepo.existsByOwnerEmail(employerEmail)) {
            throw new IllegalArgumentException("Employer already has a registered company");
        }

        User owner = userRepo.findByEmail(employerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + employerEmail));

        Company company = new Company();
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());
        company.setOwner(owner);

        Company savedCompany = companyRepo.save(company);
        return convertToResponse(savedCompany);
    }

    public CompanyResponse updateCompany(int id, CompanyRequest request, String employerEmail) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        // Ownership verification
        if (company.getOwner() == null || !company.getOwner().getEmail().equalsIgnoreCase(employerEmail)) {
            throw new AccessDeniedException("You do not have permission to modify this company");
        }

        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());

        Company updatedCompany = companyRepo.save(company);
        return convertToResponse(updatedCompany);
    }

    public void deleteCompany(int id, String employerEmail) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        // Ownership verification
        if (company.getOwner() == null || !company.getOwner().getEmail().equalsIgnoreCase(employerEmail)) {
            throw new AccessDeniedException("You do not have permission to delete this company");
        }

        companyRepo.delete(company);
    }

    private CompanyResponse convertToResponse(Company company) {
        CompanyResponse response = new CompanyResponse();
        response.setId(company.getId());
        response.setName(company.getName());
        response.setWebsite(company.getWebsite());
        response.setIndustry(company.getIndustry());
        response.setLocation(company.getLocation());

        if (company.getOwner() != null) {
            response.setOwnerId(company.getOwner().getId());
        }

        return response;
    }
}
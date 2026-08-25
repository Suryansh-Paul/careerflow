package com.evan.careerflow.service;

import com.evan.careerflow.dtos.CompanyRequest;
import com.evan.careerflow.dtos.CompanyResponse;
import com.evan.careerflow.exceptionhandling.ResourceNotFoundException;
import com.evan.careerflow.models.Company;
import com.evan.careerflow.models.User;
import com.evan.careerflow.repo.CompanyRepo;
import com.evan.careerflow.repo.UserRepo;
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

    public CompanyResponse createCompany(CompanyRequest request) {
        Company company = new Company();

        System.out.println("NAME = " + request.getName());

        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());

        if (request.getOwnerId() != null) {
            User owner = userRepo.findById(request.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + request.getOwnerId()));

            company.setOwner(owner);
        }

        Company savedCompany = companyRepo.save(company);

        return convertToResponse(savedCompany);
    }

    public CompanyResponse updateCompany(int id, CompanyRequest request) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());

        if (request.getOwnerId() != null) {
            User owner = userRepo.findById(request.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id: " + request.getOwnerId()));

            company.setOwner(owner);
        }

        Company updatedCompany = companyRepo.save(company);

        return convertToResponse(updatedCompany);
    }

    public boolean deleteCompany(int id) {
        Company company = companyRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id: " + id));

        companyRepo.delete(company);

        return true;
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
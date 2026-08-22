package com.evan.careerflow.service;

import com.evan.careerflow.dtos.CompanyRequest;
import com.evan.careerflow.dtos.CompanyResponse;
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

    public CompanyService(
            CompanyRepo companyRepo,
            UserRepo userRepo) {

        this.companyRepo = companyRepo;
        this.userRepo = userRepo;
    }


    // GET all companies
    public List<CompanyResponse> getAllCompanies() {

        return companyRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // GET company by ID
    public CompanyResponse getCompanyById(int id) {

        Company company =
                companyRepo.findById(id).orElse(null);

        if (company == null) {
            return null;
        }

        return convertToResponse(company);
    }


    // POST company
    public CompanyResponse createCompany(
            CompanyRequest request) {

        Company company = new Company();

        System.out.println("NAME = " + request.getName());

        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());

        // Set owner if ownerId was provided
        if (request.getOwnerId() != null) {

            User owner =
                    userRepo.findById(request.getOwnerId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Owner not found"
                                    ));

            company.setOwner(owner);
        }

        Company savedCompany =
                companyRepo.save(company);

        return convertToResponse(savedCompany);
    }


    // PUT company
    public CompanyResponse updateCompany(
            int id,
            CompanyRequest request) {

        Company company =
                companyRepo.findById(id).orElse(null);

        if (company == null) {
            return null;
        }

        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setLocation(request.getLocation());

        // Update owner only if ownerId is provided
        if (request.getOwnerId() != null) {

            User owner =
                    userRepo.findById(request.getOwnerId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Owner not found"
                                    ));

            company.setOwner(owner);
        }

        Company updatedCompany =
                companyRepo.save(company);

        return convertToResponse(updatedCompany);
    }


    // DELETE company
    public boolean deleteCompany(int id) {

        Company company =
                companyRepo.findById(id).orElse(null);

        if (company == null) {
            return false;
        }

        companyRepo.delete(company);

        return true;
    }


    // Entity -> Response DTO
    private CompanyResponse convertToResponse(
            Company company) {

        CompanyResponse response =
                new CompanyResponse();

        response.setId(company.getId());
        response.setName(company.getName());
        response.setWebsite(company.getWebsite());
        response.setIndustry(company.getIndustry());
        response.setLocation(company.getLocation());

        if (company.getOwner() != null) {

            response.setOwnerId(
                    company.getOwner().getId()
            );
        }

        return response;
    }
}
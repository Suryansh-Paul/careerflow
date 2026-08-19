package com.evan.careerflow.service;


import com.evan.careerflow.models.Company;
import com.evan.careerflow.repo.CompanyRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {


    private final CompanyRepo companyRepo;


    public CompanyService(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }



    public List<Company> getAllCompanies(){

        return companyRepo.findAll();

    }




    public Company getCompanyById(int id){

        return companyRepo.findById(id)
                .orElse(null);

    }





    public Company createCompany(Company company){

        return companyRepo.save(company);

    }





    public Company updateCompany(int id, Company company){


        Company existingCompany =
                companyRepo.findById(id)
                        .orElse(null);



        if(existingCompany != null){


            existingCompany.setName(
                    company.getName()
            );


            existingCompany.setWebsite(
                    company.getWebsite()
            );


            existingCompany.setIndustry(
                    company.getIndustry()
            );


            existingCompany.setLocation(
                    company.getLocation()
            );


            existingCompany.setOwner(
                    company.getOwner()
            );


            return companyRepo.save(existingCompany);

        }


        return null;

    }





    public void deleteCompany(int id){

        companyRepo.deleteById(id);

    }

}
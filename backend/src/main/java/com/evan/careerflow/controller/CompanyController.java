package com.evan.careerflow.controller;


import com.evan.careerflow.models.Company;
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


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }



    @GetMapping("/companies")
    public ResponseEntity<List<Company>> getAllCompanies(){

        return new ResponseEntity<>(
                companyService.getAllCompanies(),
                HttpStatus.OK
        );
    }




    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompany(
            @PathVariable int id){


        Company company =
                companyService.getCompanyById(id);



        if(company != null){

            return new ResponseEntity<>(
                    company,
                    HttpStatus.OK
            );
        }



        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }





    @PostMapping("/company")
    public ResponseEntity<?> createCompany(
            @RequestBody Company company){


        try{


            Company savedCompany =
                    companyService.createCompany(company);



            return new ResponseEntity<>(
                    savedCompany,
                    HttpStatus.CREATED
            );


        }catch(Exception e){


            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }





    @PutMapping("/company/{id}")
    public ResponseEntity<?> updateCompany(
            @PathVariable int id,
            @RequestBody Company company){



        Company updatedCompany =
                companyService.updateCompany(id,company);



        if(updatedCompany != null){

            return new ResponseEntity<>(
                    updatedCompany,
                    HttpStatus.OK
            );
        }



        return new ResponseEntity<>(
                "Company not found",
                HttpStatus.BAD_REQUEST
        );

    }





    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> deleteCompany(
            @PathVariable int id){


        Company company =
                companyService.getCompanyById(id);



        if(company != null){

            companyService.deleteCompany(id);


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
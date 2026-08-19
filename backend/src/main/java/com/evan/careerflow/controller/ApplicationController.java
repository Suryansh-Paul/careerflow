package com.evan.careerflow.controller;


import com.evan.careerflow.models.Application;
import com.evan.careerflow.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApplicationController {


    private final ApplicationService applicationService;


    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }



    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications(){

        return new ResponseEntity<>(
                applicationService.getAllApplications(),
                HttpStatus.OK
        );
    }




    @GetMapping("/application/{id}")
    public ResponseEntity<Application> getApplicationById(
            @PathVariable int id){


        Application application =
                applicationService.getApplicationById(id);



        if(application != null){

            return new ResponseEntity<>(
                    application,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }





    @PostMapping("/application")
    public ResponseEntity<?> createApplication(
            @RequestBody Application application){


        try{

            Application savedApplication =
                    applicationService.createApplication(application);


            return new ResponseEntity<>(
                    savedApplication,
                    HttpStatus.CREATED
            );


        }catch(Exception e){

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }





    @PutMapping("/application/{id}")
    public ResponseEntity<?> updateApplication(
            @PathVariable int id,
            @RequestBody Application application){


        Application updatedApplication =
                applicationService.updateApplication(id,application);



        if(updatedApplication != null){

            return new ResponseEntity<>(
                    updatedApplication,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "Application not found",
                HttpStatus.BAD_REQUEST
        );
    }





    @DeleteMapping("/application/{id}")
    public ResponseEntity<String> deleteApplication(
            @PathVariable int id){



        Application application =
                applicationService.getApplicationById(id);



        if(application != null){

            applicationService.deleteApplication(id);


            return new ResponseEntity<>(
                    "Application deleted successfully",
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                "Application not found",
                HttpStatus.NOT_FOUND
        );
    }

}
package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.ApplicationRequest;
import com.evan.careerflow.dtos.ApplicationResponse;
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
    public ResponseEntity<List<ApplicationResponse>> getAllApplications(){
        return new ResponseEntity<>(
                applicationService.getAllApplications(),
                HttpStatus.OK
        );
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable int id){
        return new ResponseEntity<>(
                applicationService.getApplicationById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/application")
    public ResponseEntity<ApplicationResponse> createApplication(@RequestBody ApplicationRequest request){
        ApplicationResponse savedApplication = applicationService.createApplication(request);
        return new ResponseEntity<>(
                savedApplication,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/application/{id}")
    public ResponseEntity<ApplicationResponse> updateApplication(@PathVariable int id, @RequestBody ApplicationRequest request){
        ApplicationResponse updatedApplication = applicationService.updateApplication(id, request);
        return new ResponseEntity<>(
                updatedApplication,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/application/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable int id){
        applicationService.deleteApplication(id);
        return new ResponseEntity<>(
                "Application deleted successfully",
                HttpStatus.OK
        );
    }
}
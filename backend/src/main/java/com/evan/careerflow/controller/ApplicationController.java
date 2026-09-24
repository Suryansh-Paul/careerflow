package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.ApplicationRequest;
import com.evan.careerflow.dtos.ApplicationResponse;
import com.evan.careerflow.dtos.ApplicationStatusUpdateRequest;
import com.evan.careerflow.service.ApplicationService;
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
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Candidate views their own applications
    @PreAuthorize("hasRole('CANDIDATE')")
    @GetMapping("/applications/me")
    public ResponseEntity<List<ApplicationResponse>> getMyApplications(Principal principal) {
        return new ResponseEntity<>(
                applicationService.getMyApplications(principal.getName()),
                HttpStatus.OK
        );
    }

    // Employer views applications submitted for a specific job
    @PreAuthorize("hasRole('EMPLOYER')")
    @GetMapping("/applications/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByJob(@PathVariable int jobId) {
        return new ResponseEntity<>(
                applicationService.getApplicationsByJob(jobId),
                HttpStatus.OK
        );
    }

    // Single application lookup
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/application/{id}")
    public ResponseEntity<ApplicationResponse> getApplicationById(@PathVariable int id) {
        return new ResponseEntity<>(
                applicationService.getApplicationById(id),
                HttpStatus.OK
        );
    }

    // Only CANDIDATE can apply; candidateId and initial status are resolved on the server
    @PreAuthorize("hasRole('CANDIDATE')")
    @PostMapping("/application")
    public ResponseEntity<ApplicationResponse> createApplication(
            @Valid @RequestBody ApplicationRequest request,
            Principal principal
    ) {
        ApplicationResponse savedApplication = applicationService.createApplication(request, principal.getName());
        return new ResponseEntity<>(
                savedApplication,
                HttpStatus.CREATED
        );
    }

    // Only EMPLOYER can move status (SCREENING, INTERVIEW, OFFER, REJECTED)
    @PreAuthorize("hasRole('EMPLOYER')")
    @PutMapping("/application/{id}/status")
    public ResponseEntity<ApplicationResponse> updateApplicationStatus(
            @PathVariable int id,
            @Valid @RequestBody ApplicationStatusUpdateRequest request
    ) {
        ApplicationResponse updatedApplication = applicationService.updateApplicationStatus(id, request);
        return new ResponseEntity<>(
                updatedApplication,
                HttpStatus.OK
        );
    }

    // Only EMPLOYER can remove an application record
    @PreAuthorize("hasRole('EMPLOYER')")
    @DeleteMapping("/application/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable int id) {
        applicationService.deleteApplication(id);
        return new ResponseEntity<>(
                "Application deleted successfully",
                HttpStatus.OK
        );
    }
}
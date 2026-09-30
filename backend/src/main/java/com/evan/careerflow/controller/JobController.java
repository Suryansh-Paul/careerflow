package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.JobRequest;
import com.evan.careerflow.dtos.JobResponse;
import com.evan.careerflow.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<Page<JobResponse>> getAllJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return new ResponseEntity<>(
                jobService.getAllJobs(page, size),
                HttpStatus.OK
        );
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable int id){
        return new ResponseEntity<>(
                jobService.getJobById(id),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('EMPLOYER')")
    @PostMapping("/job")
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody JobRequest request, Principal principal){
        JobResponse savedJob = jobService.createJob(request);
        return new ResponseEntity<>(
                savedJob,
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasRole('EMPLOYER')")
    @PutMapping("/job/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable int id, @Valid @RequestBody JobRequest request, Principal principal){
        JobResponse updatedJob = jobService.updateJob(id, request);
        return new ResponseEntity<>(
                updatedJob,
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasRole('EMPLOYER')")
    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id, Principal principal){
        jobService.deleteJob(id);
        return new ResponseEntity<>(
                "Job deleted successfully",
                HttpStatus.OK
        );
    }

    @GetMapping("/jobs/search")
    public ResponseEntity<Page<JobResponse>> searchJobs(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return new ResponseEntity<>(
                jobService.searchJobs(keyword, page, size),
                HttpStatus.OK
        );
    }
}
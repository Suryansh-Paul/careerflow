package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.JobRequest;
import com.evan.careerflow.dtos.JobResponse;
import com.evan.careerflow.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobResponse>> getAllJobs(){
        return new ResponseEntity<>(
                jobService.getAllJobs(),
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

    @PostMapping("/job")
    public ResponseEntity<JobResponse> createJob(@RequestBody JobRequest request){
        JobResponse savedJob = jobService.createJob(request);
        return new ResponseEntity<>(
                savedJob,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/job/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable int id, @RequestBody JobRequest request){
        JobResponse updatedJob = jobService.updateJob(id, request);
        return new ResponseEntity<>(
                updatedJob,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable int id){
        jobService.deleteJob(id);
        return new ResponseEntity<>(
                "Job deleted successfully",
                HttpStatus.OK
        );
    }

    @GetMapping("/jobs/search")
    public ResponseEntity<List<JobResponse>> searchJobs(@RequestParam String keyword){
        return new ResponseEntity<>(
                jobService.searchJobs(keyword),
                HttpStatus.OK
        );
    }
}
package com.evan.careerflow.controller;


import com.evan.careerflow.models.Job;
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
    public ResponseEntity<List<Job>> getAllJobs(){

        return new ResponseEntity<>(
                jobService.getAllJobs(),
                HttpStatus.OK
        );
    }



    @GetMapping("/job/{id}")
    public ResponseEntity<Job> getJobById(
            @PathVariable int id){


        Job job = jobService.getJobById(id);


        if(job != null){

            return new ResponseEntity<>(
                    job,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }




    @PostMapping("/job")
    public ResponseEntity<?> createJob(
            @RequestBody Job job){


        try{

            Job savedJob =
                    jobService.createJob(job);


            return new ResponseEntity<>(
                    savedJob,
                    HttpStatus.CREATED
            );


        }catch(Exception e){

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }





    @PutMapping("/job/{id}")
    public ResponseEntity<?> updateJob(
            @PathVariable int id,
            @RequestBody Job job){



        Job updatedJob =
                jobService.updateJob(id,job);



        if(updatedJob != null){

            return new ResponseEntity<>(
                    updatedJob,
                    HttpStatus.OK
            );
        }



        return new ResponseEntity<>(
                "Job not found",
                HttpStatus.BAD_REQUEST
        );
    }




    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable int id){



        Job job =
                jobService.getJobById(id);



        if(job != null){

            jobService.deleteJob(id);


            return new ResponseEntity<>(
                    "Job deleted successfully",
                    HttpStatus.OK
            );
        }



        return new ResponseEntity<>(
                "Job not found",
                HttpStatus.NOT_FOUND
        );
    }




    @GetMapping("/jobs/search")
    public ResponseEntity<List<Job>> searchJobs(
            @RequestParam String keyword){


        return new ResponseEntity<>(
                jobService.searchJobs(keyword),
                HttpStatus.OK
        );
    }

}
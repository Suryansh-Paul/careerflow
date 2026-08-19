package com.evan.careerflow.controller;


import com.evan.careerflow.models.Resume;
import com.evan.careerflow.service.ResumeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ResumeController {


    private final ResumeService resumeService;


    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }



    @GetMapping("/resumes")
    public ResponseEntity<List<Resume>> getAllResumes(){

        return new ResponseEntity<>(
                resumeService.getAllResumes(),
                HttpStatus.OK
        );
    }



    @GetMapping("/resume/{id}")
    public ResponseEntity<Resume> getResumeById(
            @PathVariable int id){


        Resume resume =
                resumeService.getResumeById(id);



        if(resume != null){

            return new ResponseEntity<>(
                    resume,
                    HttpStatus.OK
            );
        }


        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );
    }





    @PostMapping("/resume")
    public ResponseEntity<?> createResume(
            @RequestBody Resume resume){


        try{

            Resume savedResume =
                    resumeService.createResume(resume);


            return new ResponseEntity<>(
                    savedResume,
                    HttpStatus.CREATED
            );


        }catch(Exception e){

            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }





    @DeleteMapping("/resume/{id}")
    public ResponseEntity<String> deleteResume(
            @PathVariable int id){


        resumeService.deleteResume(id);


        return new ResponseEntity<>(
                "Resume deleted successfully",
                HttpStatus.OK
        );
    }

}
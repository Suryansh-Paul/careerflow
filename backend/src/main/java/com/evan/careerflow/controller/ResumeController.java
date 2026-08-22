package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.ResumeRequest;
import com.evan.careerflow.dtos.ResumeResponse;
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
    public ResponseEntity<List<ResumeResponse>> getAllResumes(){
        return new ResponseEntity<>(
                resumeService.getAllResumes(),
                HttpStatus.OK
        );
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<ResumeResponse> getResumeById(@PathVariable int id){
        return new ResponseEntity<>(
                resumeService.getResumeById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/resume")
    public ResponseEntity<ResumeResponse> createResume(@RequestBody ResumeRequest request){
        ResumeResponse savedResume = resumeService.createResume(request);
        return new ResponseEntity<>(
                savedResume,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/resume/{id}")
    public ResponseEntity<ResumeResponse> updateResume(@PathVariable int id, @RequestBody ResumeRequest request){
        ResumeResponse updatedResume = resumeService.updateResume(id, request);
        return new ResponseEntity<>(
                updatedResume,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/resume/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable int id){
        resumeService.deleteResume(id);
        return new ResponseEntity<>(
                "Resume deleted successfully",
                HttpStatus.OK
        );
    }
}
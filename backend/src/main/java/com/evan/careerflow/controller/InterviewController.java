package com.evan.careerflow.controller;

import com.evan.careerflow.dtos.InterviewRequest;
import com.evan.careerflow.dtos.InterviewResponse;
import com.evan.careerflow.service.InterviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping("/interviews")
    public ResponseEntity<List<InterviewResponse>> getAllInterviews(){
        return new ResponseEntity<>(
                interviewService.getAllInterviews(),
                HttpStatus.OK
        );
    }

    @GetMapping("/interview/{id}")
    public ResponseEntity<InterviewResponse> getInterviewById(@PathVariable int id){
        return new ResponseEntity<>(
                interviewService.getInterviewById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/interview")
    public ResponseEntity<InterviewResponse> createInterview(@RequestBody InterviewRequest request){
        InterviewResponse savedInterview = interviewService.createInterview(request);
        return new ResponseEntity<>(
                savedInterview,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/interview/{id}")
    public ResponseEntity<InterviewResponse> updateInterview(@PathVariable int id, @RequestBody InterviewRequest request){
        InterviewResponse updatedInterview = interviewService.updateInterview(id, request);
        return new ResponseEntity<>(
                updatedInterview,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/interview/{id}")
    public ResponseEntity<String> deleteInterview(@PathVariable int id){
        interviewService.deleteInterview(id);
        return new ResponseEntity<>(
                "Interview deleted successfully",
                HttpStatus.OK
        );
    }
}
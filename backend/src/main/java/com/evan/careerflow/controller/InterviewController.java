package com.evan.careerflow.controller;


import com.evan.careerflow.models.Interview;
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
    public ResponseEntity<List<Interview>> getAllInterviews(){


        return new ResponseEntity<>(
                interviewService.getAllInterviews(),
                HttpStatus.OK
        );

    }





    @GetMapping("/interview/{id}")
    public ResponseEntity<Interview> getInterviewById(
            @PathVariable int id){



        Interview interview =
                interviewService.getInterviewById(id);



        if(interview != null){

            return new ResponseEntity<>(
                    interview,
                    HttpStatus.OK
            );
        }




        return new ResponseEntity<>(
                HttpStatus.NOT_FOUND
        );

    }





    @PostMapping("/interview")
    public ResponseEntity<?> createInterview(
            @RequestBody Interview interview){


        try{


            Interview savedInterview =
                    interviewService.createInterview(interview);



            return new ResponseEntity<>(
                    savedInterview,
                    HttpStatus.CREATED
            );



        }catch(Exception e){


            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }





    @PutMapping("/interview/{id}")
    public ResponseEntity<?> updateInterview(
            @PathVariable int id,
            @RequestBody Interview interview){



        Interview updatedInterview =
                interviewService.updateInterview(id,interview);




        if(updatedInterview != null){


            return new ResponseEntity<>(
                    updatedInterview,
                    HttpStatus.OK
            );

        }



        return new ResponseEntity<>(
                "Interview not found",
                HttpStatus.BAD_REQUEST
        );

    }





    @DeleteMapping("/interview/{id}")
    public ResponseEntity<String> deleteInterview(
            @PathVariable int id){



        Interview interview =
                interviewService.getInterviewById(id);




        if(interview != null){


            interviewService.deleteInterview(id);



            return new ResponseEntity<>(
                    "Interview deleted successfully",
                    HttpStatus.OK
            );

        }



        return new ResponseEntity<>(
                "Interview not found",
                HttpStatus.NOT_FOUND
        );

    }

}
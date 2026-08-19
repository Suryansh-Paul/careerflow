package com.evan.careerflow.service;


import com.evan.careerflow.models.Interview;
import com.evan.careerflow.repo.InterviewRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InterviewService {


    private final InterviewRepo interviewRepo;


    public InterviewService(InterviewRepo interviewRepo) {
        this.interviewRepo = interviewRepo;
    }




    public List<Interview> getAllInterviews(){

        return interviewRepo.findAll();

    }





    public Interview getInterviewById(int id){

        return interviewRepo.findById(id)
                .orElse(null);

    }





    public Interview createInterview(Interview interview){

        return interviewRepo.save(interview);

    }





    public Interview updateInterview(int id, Interview interview){


        Interview existingInterview =
                interviewRepo.findById(id)
                        .orElse(null);



        if(existingInterview != null){


            existingInterview.setRound(
                    interview.getRound()
            );


            existingInterview.setInterviewDate(
                    interview.getInterviewDate()
            );


            existingInterview.setMode(
                    interview.getMode()
            );


            existingInterview.setFeedback(
                    interview.getFeedback()
            );


            existingInterview.setStatus(
                    interview.getStatus()
            );


            existingInterview.setApplication(
                    interview.getApplication()
            );


            return interviewRepo.save(existingInterview);

        }


        return null;

    }





    public void deleteInterview(int id){

        interviewRepo.deleteById(id);

    }

}
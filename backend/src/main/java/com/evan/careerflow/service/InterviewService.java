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
                .orElseThrow();

    }


    public Interview createInterview(Interview interview){

        return interviewRepo.save(interview);

    }


    public void deleteInterview(int id){

        interviewRepo.deleteById(id);

    }

}
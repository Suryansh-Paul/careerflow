package com.evan.careerflow.service;

import com.evan.careerflow.dtos.InterviewRequest;
import com.evan.careerflow.dtos.InterviewResponse;
import com.evan.careerflow.exceptionhandling.ResourceNotFoundException;
import com.evan.careerflow.models.Application;
import com.evan.careerflow.models.Interview;
import com.evan.careerflow.repo.ApplicationRepo;
import com.evan.careerflow.repo.InterviewRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewService {

    private final InterviewRepo interviewRepo;
    private final ApplicationRepo applicationRepo;

    public InterviewService(InterviewRepo interviewRepo, ApplicationRepo applicationRepo) {
        this.interviewRepo = interviewRepo;
        this.applicationRepo = applicationRepo;
    }

    public List<InterviewResponse> getAllInterviews(){
        return interviewRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public InterviewResponse getInterviewById(int id){
        Interview interview = interviewRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with ID: " + id));
        return mapToResponse(interview);
    }

    public InterviewResponse createInterview(InterviewRequest request){
        Application application = applicationRepo.findById(request.getApplicationId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + request.getApplicationId()));

        Interview interview = new Interview();
        interview.setRound(request.getRound());
        interview.setInterviewDate(request.getInterviewDate());
        interview.setMode(request.getMode());
        interview.setFeedback(request.getFeedback());
        interview.setStatus(request.getStatus());
        interview.setApplication(application);

        Interview savedInterview = interviewRepo.save(interview);
        return mapToResponse(savedInterview);
    }

    public InterviewResponse updateInterview(int id, InterviewRequest request){
        Interview existingInterview = interviewRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview not found with ID: " + id));

        if (!existingInterview.getApplication().getId().equals(request.getApplicationId())) {
            Application newApplication = applicationRepo.findById(request.getApplicationId())
                    .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + request.getApplicationId()));
            existingInterview.setApplication(newApplication);
        }

        existingInterview.setRound(request.getRound());
        existingInterview.setInterviewDate(request.getInterviewDate());
        existingInterview.setMode(request.getMode());
        existingInterview.setFeedback(request.getFeedback());
        existingInterview.setStatus(request.getStatus());

        Interview updatedInterview = interviewRepo.save(existingInterview);
        return mapToResponse(updatedInterview);
    }

    public void deleteInterview(int id){
        if (!interviewRepo.existsById(id)) {
            throw new ResourceNotFoundException("Interview not found with ID: " + id);
        }
        interviewRepo.deleteById(id);
    }

    private InterviewResponse mapToResponse(Interview interview) {
        InterviewResponse response = new InterviewResponse();

        response.setId(interview.getId());
        response.setRound(interview.getRound());
        response.setInterviewDate(interview.getInterviewDate());
        response.setMode(interview.getMode());
        response.setFeedback(interview.getFeedback());
        response.setStatus(interview.getStatus());

        if (interview.getApplication() != null) {
            response.setApplicationId(interview.getApplication().getId());
        }

        return response;
    }
}
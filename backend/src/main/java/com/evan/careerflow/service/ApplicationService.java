package com.evan.careerflow.service;

import com.evan.careerflow.dtos.ApplicationRequest;
import com.evan.careerflow.dtos.ApplicationResponse;
import com.evan.careerflow.exceptionhandling.ResourceNotFoundException;
import com.evan.careerflow.models.Application;
import com.evan.careerflow.models.Job;
import com.evan.careerflow.models.User;
import com.evan.careerflow.repo.ApplicationRepo;
import com.evan.careerflow.repo.JobRepo;
import com.evan.careerflow.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepo applicationRepo;
    private final UserRepo userRepo;
    private final JobRepo jobRepo;

    public ApplicationService(ApplicationRepo applicationRepo, UserRepo userRepo, JobRepo jobRepo) {
        this.applicationRepo = applicationRepo;
        this.userRepo = userRepo;
        this.jobRepo = jobRepo;
    }

    public List<ApplicationResponse> getAllApplications(){
        return applicationRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ApplicationResponse getApplicationById(int id){
        Application application = applicationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + id));
        return mapToResponse(application);
    }

    public ApplicationResponse createApplication(ApplicationRequest request){
        User candidate = userRepo.findById(request.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + request.getCandidateId()));

        Job job = jobRepo.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + request.getJobId()));

        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(request.getStatus());
        application.setNotes(request.getNotes());

        Application savedApplication = applicationRepo.save(application);
        return mapToResponse(savedApplication);
    }

    public ApplicationResponse updateApplication(int id, ApplicationRequest request){
        Application existingApplication = applicationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + id));

        if (!existingApplication.getCandidate().getId().equals(request.getCandidateId())) {
            User newCandidate = userRepo.findById(request.getCandidateId())
                    .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + request.getCandidateId()));
            existingApplication.setCandidate(newCandidate);
        }

        if (!existingApplication.getJob().getId().equals(request.getJobId())) {
            Job newJob = jobRepo.findById(request.getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + request.getJobId()));
            existingApplication.setJob(newJob);
        }

        existingApplication.setStatus(request.getStatus());
        existingApplication.setNotes(request.getNotes());

        Application updatedApplication = applicationRepo.save(existingApplication);
        return mapToResponse(updatedApplication);
    }

    public void deleteApplication(int id){
        if (!applicationRepo.existsById(id)) {
            throw new ResourceNotFoundException("Application not found with ID: " + id);
        }
        applicationRepo.deleteById(id);
    }

    private ApplicationResponse mapToResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();
        response.setId(application.getId());
        response.setStatus(application.getStatus());
        response.setAppliedAt(application.getAppliedAt());
        response.setNotes(application.getNotes());

        if (application.getCandidate() != null) {
            response.setCandidateId(application.getCandidate().getId());
        }

        if (application.getJob() != null) {
            response.setJobId(application.getJob().getId());
        }

        return response;
    }
}
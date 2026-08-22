package com.evan.careerflow.service;

import com.evan.careerflow.dtos.ResumeRequest;
import com.evan.careerflow.dtos.ResumeResponse;
import com.evan.careerflow.models.Resume;
import com.evan.careerflow.models.User;
import com.evan.careerflow.repo.ResumeRepo;
import com.evan.careerflow.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    private final ResumeRepo resumeRepo;
    private final UserRepo userRepo;

    public ResumeService(ResumeRepo resumeRepo, UserRepo userRepo) {
        this.resumeRepo = resumeRepo;
        this.userRepo = userRepo;
    }

    public List<ResumeResponse> getAllResumes(){
        return resumeRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ResumeResponse getResumeById(int id){
        Resume resume = resumeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found with ID: " + id));
        return mapToResponse(resume);
    }

    public ResumeResponse createResume(ResumeRequest request){
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + request.getUserId()));

        Resume resume = new Resume();
        resume.setFileName(request.getFileName());
        resume.setFileUrl(request.getFileUrl());
        resume.setScore(request.getScore());
        resume.setUser(user);

        Resume savedResume = resumeRepo.save(resume);
        return mapToResponse(savedResume);
    }

    public ResumeResponse updateResume(int id, ResumeRequest request){
        Resume existingResume = resumeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found with ID: " + id));

        if (request.getUserId() != null) {
            if (existingResume.getUser() == null || !existingResume.getUser().getId().equals(request.getUserId())) {
                User newUser = userRepo.findById(request.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + request.getUserId()));
                existingResume.setUser(newUser);
            }
        }

        existingResume.setFileName(request.getFileName());
        existingResume.setFileUrl(request.getFileUrl());
        existingResume.setScore(request.getScore());

        Resume updatedResume = resumeRepo.save(existingResume);
        return mapToResponse(updatedResume);
    }


    public void deleteResume(int id){
        if (!resumeRepo.existsById(id)) {
            throw new EntityNotFoundException("Resume not found with ID: " + id);
        }
        resumeRepo.deleteById(id);
    }

    private ResumeResponse mapToResponse(Resume resume) {
        ResumeResponse response = new ResumeResponse();
        response.setId(resume.getId());
        response.setFileName(resume.getFileName());
        response.setFileUrl(resume.getFileUrl());
        response.setScore(resume.getScore());
        response.setUploadedAt(resume.getUploadedAt());

        if (resume.getUser() != null) {
            response.setUserId(resume.getUser().getId());
        }

        return response;
    }
}
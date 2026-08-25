package com.evan.careerflow.service;

import com.evan.careerflow.dtos.JobRequest;
import com.evan.careerflow.dtos.JobResponse;
import com.evan.careerflow.exceptionhandling.ResourceNotFoundException;
import com.evan.careerflow.models.Company;
import com.evan.careerflow.models.Job;
import com.evan.careerflow.repo.CompanyRepo;
import com.evan.careerflow.repo.JobRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepo jobRepo;
    private final CompanyRepo companyRepo;

    public JobService(JobRepo jobRepo, CompanyRepo companyRepo) {
        this.jobRepo = jobRepo;
        this.companyRepo = companyRepo;
    }

    public List<JobResponse> getAllJobs() {
        return jobRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public JobResponse getJobById(int id) {
        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));
        return mapToResponse(job);
    }

    public JobResponse createJob(JobRequest request) {
        Company company = companyRepo.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + request.getCompanyId()));

        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setCompany(company);

        Job savedJob = jobRepo.save(job);

        return mapToResponse(savedJob);
    }

    public JobResponse updateJob(int id, JobRequest request) {
        Job existingJob = jobRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + id));

        if (!existingJob.getCompany().getId().equals(request.getCompanyId())) {
            Company newCompany = companyRepo.findById(request.getCompanyId())
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + request.getCompanyId()));
            existingJob.setCompany(newCompany);
        }

        existingJob.setTitle(request.getTitle());
        existingJob.setDescription(request.getDescription());
        existingJob.setLocation(request.getLocation());
        existingJob.setJobType(request.getJobType());

        Job updatedJob = jobRepo.save(existingJob);

        return mapToResponse(updatedJob);
    }

    public void deleteJob(int id) {
        if (!jobRepo.existsById(id)) {
            throw new ResourceNotFoundException("Job not found with ID: " + id);
        }
        jobRepo.deleteById(id);
    }

    public List<JobResponse> searchJobs(String keyword) {
        return jobRepo.searchJobs(keyword)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private JobResponse mapToResponse(Job job) {
        JobResponse response = new JobResponse();

        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setDescription(job.getDescription());
        response.setLocation(job.getLocation());
        response.setJobType(job.getJobType());
        response.setCreatedAt(job.getCreatedAt());

        if (job.getCompany() != null) {
            response.setCompanyId(job.getCompany().getId());
        }

        return response;
    }
}
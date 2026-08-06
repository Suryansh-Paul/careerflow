package com.evan.careerflow.service;


import com.evan.careerflow.models.Job;
import com.evan.careerflow.repo.JobRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobService {


    private final JobRepo jobRepo;


    public JobService(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }


    public List<Job> getAllJobs(){

        return jobRepo.findAll();

    }


    public Job getJobById(int id){

        return jobRepo.findById(id)
                .orElseThrow();

    }


    public Job createJob(Job job){

        return jobRepo.save(job);

    }


    public List<Job> searchJobs(String keyword){

        return jobRepo.searchJobs(keyword);

    }


    public void deleteJob(int id){

        jobRepo.deleteById(id);

    }

}
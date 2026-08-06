package com.evan.careerflow.service;


import com.evan.careerflow.models.Resume;
import com.evan.careerflow.repo.ResumeRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResumeService {


    private final ResumeRepo resumeRepo;


    public ResumeService(ResumeRepo resumeRepo) {
        this.resumeRepo = resumeRepo;
    }


    public List<Resume> getAllResumes(){

        return resumeRepo.findAll();

    }


    public Resume getResumeById(int id){

        return resumeRepo.findById(id)
                .orElseThrow();

    }


    public Resume createResume(Resume resume){

        return resumeRepo.save(resume);

    }


    public void deleteResume(int id){

        resumeRepo.deleteById(id);

    }

}


package com.evan.careerflow.service;


import com.evan.careerflow.models.Application;
import com.evan.careerflow.repo.ApplicationRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ApplicationService {


    private final ApplicationRepo applicationRepo;


    public ApplicationService(ApplicationRepo applicationRepo) {
        this.applicationRepo = applicationRepo;
    }



    public List<Application> getAllApplications(){

        return applicationRepo.findAll();

    }




    public Application getApplicationById(int id){

        return applicationRepo.findById(id)
                .orElse(null);

    }





    public Application createApplication(Application application){

        return applicationRepo.save(application);

    }





    public Application updateApplication(int id, Application application){


        Application existingApplication =
                applicationRepo.findById(id)
                        .orElse(null);



        if(existingApplication != null){


            existingApplication.setCandidate(
                    application.getCandidate()
            );


            existingApplication.setJob(
                    application.getJob()
            );


            existingApplication.setStatus(
                    application.getStatus()
            );


            existingApplication.setNotes(
                    application.getNotes()
            );



            return applicationRepo.save(existingApplication);

        }


        return null;

    }





    public void deleteApplication(int id){

        applicationRepo.deleteById(id);

    }

}
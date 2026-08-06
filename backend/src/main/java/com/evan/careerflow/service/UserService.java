package com.evan.careerflow.service;


import com.evan.careerflow.models.User;
import com.evan.careerflow.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    private final UserRepo userRepo;


    public UserService(UserRepo userRepo){

        this.userRepo = userRepo;

    }



    public List<User> getAllUsers(){

        return userRepo.findAll();

    }



    public User getUserById(int id){

        return userRepo.findById(id)
                .orElseThrow();

    }



    public User createUser(User user){

        return userRepo.save(user);

    }



    public User updateUser(int id, User user){

        User existingUser = userRepo.findById(id)
                .orElseThrow();


        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());


        return userRepo.save(existingUser);

    }



    public void deleteUser(int id){

        userRepo.deleteById(id);

    }

}
package com.evan.careerflow.service;

import com.evan.careerflow.dtos.UserRequest;
import com.evan.careerflow.dtos.UserResponse;
import com.evan.careerflow.exceptionhandling.ResourceNotFoundException;
import com.evan.careerflow.models.User;
import com.evan.careerflow.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserResponse> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public UserResponse getUserById(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return convertToResponse(user);
    }

    public UserResponse createUser(UserRequest request) {
        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setEnabled(true);

        User savedUser = userRepo.save(user);

        return convertToResponse(savedUser);
    }

    public UserResponse updateUser(int id, UserRequest request) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User updatedUser = userRepo.save(user);

        return convertToResponse(updatedUser);
    }

    public boolean deleteUser(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepo.delete(user);

        return true;
    }

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setEnabled(user.isEnabled());
        response.setRole(user.getRole());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }
}
package com.evan.careerflow.service;

import com.evan.careerflow.dtos.UserRequest;
import com.evan.careerflow.dtos.UserResponse;
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


    // GET all users
    public List<UserResponse> getAllUsers() {

        return userRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // GET user by ID
    public UserResponse getUserById(int id) {

        User user = userRepo.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        return convertToResponse(user);
    }


    // POST user
    public UserResponse createUser(UserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        // Server controlled
        user.setEnabled(true);

        User savedUser = userRepo.save(user);

        return convertToResponse(savedUser);
    }


    // PUT user
    public UserResponse updateUser(int id, UserRequest request) {

        User user = userRepo.findById(id).orElse(null);

        if (user == null) {
            return null;
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User updatedUser = userRepo.save(user);

        return convertToResponse(updatedUser);
    }


    // DELETE user
    public boolean deleteUser(int id) {

        User user = userRepo.findById(id).orElse(null);

        if (user == null) {
            return false;
        }

        userRepo.delete(user);

        return true;
    }


    // Entity -> Response DTO
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
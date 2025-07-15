// ✅ Declares the package for the UserService class
package com.malik.springSecEx.service;

// ✅ Imports necessary classes for model, repository, encryption, and Spring service annotation
import com.malik.springSecEx.model.Users;                       // Model/entity class representing the user
import com.malik.springSecEx.repo.UserRepo;                     // Repository interface to interact with the database
import org.springframework.beans.factory.annotation.Autowired;  // Enables automatic dependency injection
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // For securely hashing passwords
import org.springframework.stereotype.Service;                  // Marks this as a Spring service class


// ✅ Marks this class as a Spring-managed service, used in the business logic layer
@Service
public class UserService {


    // ✅ Injects the UserRepo instance to interact with the user table in the database
    @Autowired
    private UserRepo repo;


    // ✅ Defines a BCrypt encoder with strength 12 for hashing passwords
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // ✅ Public method to register a new user
    public Users register(Users user) {

        // ✅ Hashes the user's plain text password before saving it
        user.setPassword(encoder.encode(user.getPassword()));

        // ✅ Saves the user to the database via UserRepo
        repo.save(user);

        // ✅ Returns the saved user (with encoded password)
        return user;
    }
}

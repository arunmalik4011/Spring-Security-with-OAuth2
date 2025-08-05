// ✅ Declares the package for the UserService class
package com.malik.springSecEx.service;

// ✅ Imports necessary classes for model, repository, encryption, and Spring service annotation
import com.malik.springSecEx.model.Users;                       // Model/entity class representing the user
import com.malik.springSecEx.repo.UserRepo;                     // Repository interface to interact with the database
import org.springframework.beans.factory.annotation.Autowired;  // Enables automatic dependency injection
import org.springframework.stereotype.Service;                  // Marks this as a Spring service class
import java.util.List;

// ✅ Marks this class as a Spring-managed service, used in the business logic layer
@Service
public class UserService {

    // ✅ Injects the UserRepo instance to interact with the user table in the database
    @Autowired
    private UserRepo repo;

    public List<Users> getAllUser(){
       return repo.findAll();
    }
}

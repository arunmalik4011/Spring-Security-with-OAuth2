// ✅ Declares the package in which this service class resides
package com.malik.springSecEx.service;

// ✅ Imports the necessary classes
import com.malik.springSecEx.model.Student;                 // Student entity/model class
import com.malik.springSecEx.repo.StudentRepository;       // JPA repository interface for database operations
import org.springframework.beans.factory.annotation.Autowired; // For automatic dependency injection
import org.springframework.stereotype.Service;                 // Marks this class as a Spring service
import java.util.List; // Used to return a list of students

// ✅ Marks this class as a Spring-managed service component
@Service
public class StudentService {

    // ✅ Injects the StudentRepository dependency to interact with the database
    @Autowired
    private StudentRepository repo;
    // ✅ Returns a list of all students from the database
    public List<Student> getAllStudents() {
        return repo.findAll(); // Uses JpaRepository's built-in method
    }
    // ✅ Saves or updates a student in the database
    public void saveStudent(Student student) {
        repo.save(student); // Will insert or update depending on whether student has an ID
    }
    // ✅ Retrieves a single student by their ID
    public Student getById(int id) {
        // Uses Optional to return student if found, else returns null
        return repo.findById(id).orElse(null);
    }
    // ✅ Deletes a student from the database by their ID
    public void deleteById(int id) {
        repo.deleteById(id); // Uses JpaRepository's built-in method
    }
    // ✅ Searches students by name (case insensitive)
    public List<Student> searchByName(String name) {
        // Custom method defined in your StudentRepository (must follow Spring naming convention)
        return repo.findByNameContainingIgnoreCase(name);
    }
}


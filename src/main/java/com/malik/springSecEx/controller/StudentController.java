// Declares the package for this controller
package com.malik.springSecEx.controller;

// Imports required classes and annotations
import com.malik.springSecEx.model.Student;
import com.malik.springSecEx.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indicates this class is a Spring MVC controller
@Controller
public class StudentController {

    // Injects the StudentService (business logic layer)
    @Autowired
    private StudentService studentService;

    // ✅ 1. GET all students and show them in student.html
    @GetMapping("/student")
    public String getStudents(Model model) {
        model.addAttribute("student", studentService.getAllStudents()); // Adds list to model
        return "student"; // Renders student.html
    }

    // ✅ 2. Show form to add a new student
    @GetMapping("/add-student")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student()); // Binds an empty student object
        return "add-student"; // Shows add-student.html
    }

    // ✅ 3. POST - Save new or updated student (add or edit)
    @PostMapping("/student")
    public String saveOrUpdateStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student); // Save or update in DB
        return "redirect:/student"; // Redirect to student list page
    }

    // ✅ 4. Show edit form for an existing student (by ID)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = studentService.getById(id); // Fetch student by ID
        if (student != null) {
            model.addAttribute("student", student); // Bind fetched student to form
            return "add-student"; // Use same form as adding
        }
        return "redirect:/student"; // If not found, go back to list
    }

    // ✅ 5. Delete a student by ID
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteById(id); // Call service to delete
        return "redirect:/student"; // Back to list
    }

    // ✅ 6. Search students by name using request param
    @GetMapping("/student/search")
    public String searchByName(@RequestParam("name") String name, Model model) {
        List<Student> result = studentService.searchByName(name); // Call search logic
        model.addAttribute("student", result); // Bind result to model
        return "student"; // Show filtered list
    }

    // ✅ 7. View details of a specific student by ID
    @GetMapping("/student/{id}")
    public String viewStudentById(@PathVariable int id, Model model) {
        Student student = studentService.getById(id); // Get by ID
        if (student != null) {
            model.addAttribute("student", student); // Pass student to view
            return "view-student"; // Show details view
        }
        return "redirect:/student"; // If not found, back to list
    }
}

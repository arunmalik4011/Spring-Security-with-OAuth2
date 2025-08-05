package com.malik.springSecEx.controller;
import com.malik.springSecEx.model.Users;
import com.malik.springSecEx.repo.UserRepo;
import com.malik.springSecEx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepo repo;


    @GetMapping
    public String adminDashboard() {
        return "admin"; // Make sure you have `admin.html` in templates
    }


    @GetMapping("/all-users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUser()); // Adds list to model
        return "all-users"; // Renders student.html
    }


    @GetMapping("/create-admin-user")
    public String showAddForm(Model model) {
        model.addAttribute("user", new Users()); // Binds an empty student object
        return "create-admin-user"; // Shows add-student.html-
    }

    @PostMapping("/create-admin-user")
    public String registerAdmin(@ModelAttribute("user") Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setProvider("local"); // OAuth2 or local login indicator
        repo.save(user); // ✅ Already has the selected role from the form
        return "redirect:/login?registered"; // ✅ Go to login with success flag
    }


}

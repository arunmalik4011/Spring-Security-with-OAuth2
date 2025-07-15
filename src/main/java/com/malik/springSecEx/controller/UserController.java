//package com.malik.springSecEx.controller;
//import com.malik.springSecEx.model.Users;
//import com.malik.springSecEx.repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserRepo repo;
//
////    @Autowired
////    private UserService service;
//
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//
//
////    @Autowired
////    private PasswordEncoder passwordEncoder;
//
//    @GetMapping("/register")
//    public String showRegisterForm() {
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public Users register(@RequestBody Users user) {
//        // Set password encoded
//        user.setPassword(encoder.encode(user.getPassword()));
//        user.setProvider("local"); // for distinguishing local vs OAuth users
//        return repo.save(user); // Save to DB
//    }
//    }

//    @PostMapping("/register")
//    public String registerUser(@RequestParam String username, @RequestParam String password) {
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setProvider("local");
//        repo.save(user);
//        return "redirect:/login?registered";
//    }







package com.malik.springSecEx.controller;

import com.malik.springSecEx.model.Users;
import com.malik.springSecEx.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    // 🖼️ Show registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new Users()); // Bind an empty user object to the form
        return "register"; // Returns register.html
    }

    // 📝 Handle form submission
    @PostMapping("/register")
    public String register(@ModelAttribute("user") Users user) {
        // Encrypt the password before saving
        user.setPassword(encoder.encode(user.getPassword()));

        // Indicate that the user is registered via local form (not OAuth)
        user.setProvider("local");

        // Save the user to the database
        repo.save(user);

        // Redirect to login page with success flag
        return "redirect:/login?registered";
    }
}


package com.malik.springSecEx.controller;

// Importing annotations and classes for Spring MVC and security

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

// Marking this class as a Spring MVC controller that returns view names
@Controller
public class HomeController {

//    @Autowired
//    UserDetailsService userDetailsService;

    // ⬅️ Handles both "/" and "/"
    @GetMapping({"/", ""})
    public String rootRedirectToLogin() {
        return "login"; // Renders login.html
    }

    // ✅ This method handles GET requests to the root URL "/"
    // It returns the "login" view (login.html) — useful for showing a login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Renders login.html with both Google & form login
    }

    // ✅ This method handles GET requests when login fails (Spring Security redirects to /login-error)
    // It adds an error message to the model and reloads the login page with feedback
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", "❌ Invalid credentials");
        return "login"; // Show login page with error message
    }

    // ✅ After successful login (form or OAuth2), user is redirected to /home
    // This method adds user info to the model to be displayed on the home page
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OidcUser principal, Model model) {

        // If user logged in with OAuth2 (Google/GitHub), fetch and display user details
        if (principal != null) {
            model.addAttribute("name", principal.getFullName());   // User's full name
            model.addAttribute("email", principal.getEmail());     // User's email
            model.addAttribute("picture", principal.getPicture()); // User's profile picture
        } else {
            // If no OAuth2 user is found, fallback values (in case of form login or anonymous)
            model.addAttribute("name", "Guest");
            model.addAttribute("email", "Unknown");
        }
        return "home"; // Return home.html page
    }

    // 🚪 Manual Logout
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout=true"; // redirect with logout success message
    }
}


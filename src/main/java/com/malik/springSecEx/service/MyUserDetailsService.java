// ✅ Declares the package where this service class belongs.
// This helps organize your code logically within your application.
package com.malik.springSecEx.service;

// ✅ Imports required classes for working with users and Spring Security
import com.malik.springSecEx.model.UserPrincipal; // Custom class that implements Spring Security's UserDetails
import com.malik.springSecEx.model.Users;         // Your entity class representing a user in the database
import com.malik.springSecEx.repo.UserRepo;       // Repository interface for interacting with the database

// Spring annotations and core security interfaces
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;               // Main interface required by Spring Security
import org.springframework.security.core.userdetails.UserDetailsService;      // Service interface to load user-specific data
import org.springframework.security.core.userdetails.UsernameNotFoundException; // Exception thrown if user not found
import org.springframework.stereotype.Service;

// ✅ Marks this class as a service component in Spring.
// This allows Spring to detect it during component scanning and manage it as a bean.
@Service
public class MyUserDetailsService implements UserDetailsService {

    // ✅ Injects the UserRepo dependency automatically using Spring's @Autowired annotation.
    // This allows this class to use methods defined in UserRepo to fetch user data from the DB.
    @Autowired
    private UserRepo userRepo;

    /**
     * ✅ This method is a required override from UserDetailsService.
     * It is automatically called by Spring Security when a user attempts to log in.
     * It loads user details (like username, password, and roles) based on the username entered during login.
     *
     * @param username The username entered in the login form (typically email or username)
     * @return UserDetails - a Spring Security object containing user credentials and authorities
     * @throws UsernameNotFoundException if the user is not found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // ✅ Uses the repository to look up a user in the database by their username (or email).
        // This should return a Users object from your DB.
        Users user = userRepo.findByUsername(username);

        // ✅ If no user is found, this means the login attempt is invalid.
        // We throw a Spring Security-specific exception to indicate login failure.
        if (user == null) {
            System.out.println("User Not Found"); // Optional debug output for developer
            throw new UsernameNotFoundException("User not found in database with username: " + username);
        }

        // ✅ If the user is found, wrap it in a custom UserPrincipal object.
        // UserPrincipal implements UserDetails and is used internally by Spring Security.
        // It contains the user's roles, password, username, and other credentials.
        return new UserPrincipal(user);
    }
}

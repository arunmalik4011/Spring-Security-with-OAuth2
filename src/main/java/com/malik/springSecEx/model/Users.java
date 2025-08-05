// ✅ Package declaration for your model class
package com.malik.springSecEx.model;

// ✅ Importing JPA annotations for ORM mapping
import jakarta.persistence.*;

// ✅ Declares this class as a JPA Entity (maps to a DB table named 'users' by default)
@Entity
@Table(name = "users")
public class Users {
    // ✅ Primary key field
    @Id
    // ✅ Auto-generates ID using database identity (auto-increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Stores the user's login name (usually email or username)
    private String username;

    // ✅ Stores the encrypted password
    private String password;

    // ✅ Stores the login provider (e.g., "local" for form login or "google" for OAuth2)
    private String provider;

    private String role;

    @Transient
    private boolean empty;


    private boolean isEnabled;

    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    // ✅ Getter for id field
    public Long getId() {
        return id;
    }
    // ✅ Setter for id field
    public void setId(Long id) {
        this.id = id;
    }
    // ✅ Getter for username field
    public String getUsername() {
        return username;
    }
    // ✅ Setter for username field
    public void setUsername(String username) {
        this.username = username;
    }
    // ✅ Getter for password field
    public String getPassword() {
        return password;
    }
    // ✅ Setter for password field
    public void setPassword(String password) {
        this.password = password;
    }
    // ✅ Getter for provider field
    public String getProvider() {
        return provider;
    }
    // ✅ Setter for provider field
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public void isEnabled(boolean b) {
    }
    // ✅ toString method for easy printing/debugging of User object
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", provider='" + provider + '\'' +
                ", role='" + role + '\'' +
                ", isEnabled=" + isEnabled +
                ", empty=" + empty +
                '}';
    }
}

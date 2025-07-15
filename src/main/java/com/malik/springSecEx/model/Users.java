// ✅ Package declaration for your model class
package com.malik.springSecEx.model;

// ✅ Importing JPA annotations for ORM mapping
import jakarta.persistence.Entity;              // Marks this class as a JPA entity (mapped to a database table)
import jakarta.persistence.Id;                  // Specifies the primary key field
import jakarta.persistence.Table;               // (Optional here) Specifies the table name (not used in your class)
import jakarta.persistence.GeneratedValue;      // Specifies how the primary key is generated
import jakarta.persistence.GenerationType;      // Specifies the strategy for generating primary key values


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


    // ✅ toString method for easy printing/debugging of User object
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}

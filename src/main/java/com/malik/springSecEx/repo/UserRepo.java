// ✅ Declares the package where this repository interface is located
package com.malik.springSecEx.repo;
// ✅ Imports the Users entity (model class)
import com.malik.springSecEx.model.Users;
// ✅ Imports JpaRepository which provides all basic CRUD and query operations
import org.springframework.data.jpa.repository.JpaRepository;
// ✅ Marks this interface as a Spring Repository so it can be auto-detected and managed
import org.springframework.stereotype.Repository;

// ✅ Marks this interface as a Spring Data Repository component
@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    // ⬆️ Extends JpaRepository:
    //     - <Users, Integer> means this repository works with the Users entity
    //     - and the primary key (ID) type is Integer

    // ✅ Custom method to find a user by username (used in authentication)
    // Spring Data JPA automatically generates the query based on method name
    Users findByUsername(String username);
}

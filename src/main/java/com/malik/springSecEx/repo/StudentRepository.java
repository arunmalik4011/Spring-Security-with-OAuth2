// ✅ Declares the package location of this repository interface
package com.malik.springSecEx.repo;
// ✅ Imports the Student entity class which this repository will manage
import com.malik.springSecEx.model.Student;
// ✅ Imports Spring Data JPA's JpaRepository interface,
//    which provides CRUD operations and more for the Student entity
import org.springframework.data.jpa.repository.JpaRepository;
// ✅ Imports List interface from Java Collections, used in custom query method below
import java.util.List;

// ✅ This interface is a repository that works with Student entities.
// ✅ It extends JpaRepository<Student, Integer>:
//     - Student → the entity class this repo handles
//     - Integer → the type of the primary key (Student's ID is of type int)
public interface StudentRepository extends JpaRepository<Student, Integer> {

    // ✅ Custom finder method:
    // Spring Data JPA automatically creates the query for this method
    // It will return a list of students whose name contains the provided string (case-insensitive)
    List<Student> findByNameContainingIgnoreCase(String name);
}


// ✅ Declares the package this class belongs to
package com.malik.springSecEx.model;

// ✅ Imports necessary annotations from Jakarta Persistence API for JPA (ORM mapping)
import jakarta.persistence.*;

// ✅ Marks this class as a JPA Entity to be mapped to a database table
@Entity
// ✅ Specifies the table name in the database as "student"
@Table(name = "student")
public class Student {
    // ✅ Marks `id` as the primary key of the table
    @Id
    // ✅ Auto-generates the ID using the database's identity column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // ✅ Name column (implicitly mapped to `name` column in DB)
    private String name;

    // ✅ Marks column to store student's marks
    private int marks;

    // ✅ Parameterized constructor (useful for creating objects manually)
    public Student(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    // ✅ Default constructor (required by JPA)
    public Student() {
    }
    // ✅ Getter and Setter for `id`
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    // ✅ Getter and Setter for `name`
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // ✅ Getter and Setter for `marks`
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }

    // ✅ Override toString method for debugging/logging purposes
    @Override
    public String toString() {
        return "Student{" +  // (⚠️ Typo: This should probably be "Student" instead of "StudentController")
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}

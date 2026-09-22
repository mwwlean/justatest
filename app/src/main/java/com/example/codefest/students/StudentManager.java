package com.example.codefest.students;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    // =====================================================
    // SINGLETON
    // =====================================================

    private static StudentManager instance;

    // This ArrayList stores all students
    private final ArrayList<Student> studentList =
            new ArrayList<>();

    // Used to give every student a unique ID
    private int nextId = 1;


    // Private constructor
    private StudentManager() {
    }


    // Get the single instance of StudentManager
    public static synchronized StudentManager getInstance() {

        if (instance == null) {
            instance = new StudentManager();
        }

        return instance;
    }


    // =====================================================
    // CREATE
    // =====================================================

    public Student addStudent(String name, String course) {

        // Create a new Student object
        Student student =
                new Student(
                        nextId++,
                        name,
                        course
                );

        // Add student to ArrayList
        studentList.add(student);

        return student;
    }


    // =====================================================
    // READ
    // =====================================================

    public List<Student> getAllStudent() {

        // Return a copy of the list
        return new ArrayList<>(studentList);
    }


    // =====================================================
    // UPDATE
    // =====================================================

    public boolean updateStudent(
            int studentId,
            String newName,
            String newCourse
    ) {

        // Search every student
        for (Student student : studentList) {

            // Find matching ID
            if (student.getId() == studentId) {

                // Update information
                student.setName(newName);
                student.setCourse(newCourse);

                return true;
            }
        }

        // Student was not found
        return false;
    }
}
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

    // --- SEARCH / FILTER is for the note---
    public List<notes> searchNotes(String query) {
        String cleanQuery = query.trim().toLowerCase();

        // If search is blank, return everything
        if (cleanQuery.isEmpty()) {
            return getAllNotes();
        }

        ArrayList<notes> filtered = new ArrayList<>();
        for (notes note : noteList) {
            // Checks if the note text contains the search query
            if (note.getTxt().toLowerCase().contains(cleanQuery)) {
                filtered.add(note);
            }
        }
        return filtered;
    }

    2. Convert the String into an actual date
Use DateTimeFormatter :
DateTimeFormatter formatter =
DateTimeFormatter.ofPattern("MMM dd, yyyy"
, Locale.ENGLISH);
LocalDate deadline =
LocalDate.parse(deadlineText, formatter);
Now:
"Sep 30, 2026"
↓ parse
LocalDate
↓
2026-09-30
You can now actually compare it.
3. Get today's date
LocalDate today = LocalDate.now();
For example:
today = 2026-09-22
deadline = 2026-09-30
    

 // 5. Real-time search filtering (ZERO adapter needed)
        searchNotesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence queryText, int start, int before, int count) {
                // When typing in search, filter and redraw the container
                List<notes> filteredNotes = notesManager.searchNotes(queryText.toString());
                displayNotes(filteredNotes);
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });


}
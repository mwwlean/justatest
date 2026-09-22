package com.example.codefest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codefest.students.Student;
import com.example.codefest.students.StudentManager;

import java.util.List;

public class EditStudentActivity extends AppCompatActivity {

    // StudentManager
    private StudentManager manager;

    // The ID of the student we are editing
    private int studentId;

    // EditText fields
    private EditText etEditName;
    private EditText etEditCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect this Activity to the XML
        setContentView(R.layout.activity_edit_student);


        // =====================================================
        // GET MANAGER
        // =====================================================

        manager = StudentManager.getInstance();


        // =====================================================
        // GET UI ELEMENTS
        // =====================================================

        etEditName =
                findViewById(R.id.etEditName);

        etEditCourse =
                findViewById(R.id.etEditCourse);

        Button btnSave =
                findViewById(R.id.btnSave);

        Button btnCancel =
                findViewById(R.id.btnCancel);


        // =====================================================
        // GET STUDENT ID
        // =====================================================

        /*
            MainActivity will send us the ID of the student.

            Example:

            Student ID = 3

            Intent:
                studentId = 3

            We retrieve that ID here.
        */

        studentId =
                getIntent().getIntExtra(
                        "student_id",
                        -1
                );


        // =====================================================
        // FIND THE STUDENT
        // =====================================================

        Student selectedStudent = null;

        List<Student> students =
                manager.getAllStudent();

        for (Student student : students) {

            if (student.getId() == studentId) {

                selectedStudent = student;

                break;
            }
        }


        // =====================================================
        // DISPLAY CURRENT INFORMATION
        // =====================================================

        if (selectedStudent != null) {

            // Put the existing name into EditText
            etEditName.setText(
                    selectedStudent.getName()
            );

            // Put the existing course into EditText
            etEditCourse.setText(
                    selectedStudent.getCourse()
            );

        } else {

            Toast.makeText(
                    this,
                    "Student not found",
                    Toast.LENGTH_SHORT
            ).show();

            finish();
        }


        // =====================================================
        // SAVE
        // =====================================================

        btnSave.setOnClickListener(v -> {

            // Get edited information
            String newName =
                    etEditName
                            .getText()
                            .toString()
                            .trim();

            String newCourse =
                    etEditCourse
                            .getText()
                            .toString()
                            .trim();


            // =================================================
            // VALIDATION
            // =================================================

            if (newName.isEmpty() ||
                    newCourse.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // =================================================
            // UPDATE
            // =================================================

            boolean updated =
                    manager.updateStudent(
                            studentId,
                            newName,
                            newCourse
                    );


            // =================================================
            // CHECK RESULT
            // =================================================

            if (updated) {

                Toast.makeText(
                        this,
                        "Student updated!",
                        Toast.LENGTH_SHORT
                ).show();

                // Go back to MainActivity
                finish();

            } else {

                Toast.makeText(
                        this,
                        "Student not found",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        // =====================================================
        // CANCEL
        // =====================================================

        btnCancel.setOnClickListener(v -> {

            // Don't save anything.

            // Just go back.
            finish();
        });
    }
}
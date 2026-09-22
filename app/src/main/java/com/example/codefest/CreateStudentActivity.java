package com.example.codefest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codefest.students.StudentManager;

public class CreateStudentActivity extends AppCompatActivity {

    // StudentManager
    private StudentManager manager;

    // EditText fields
    private EditText etCreateName;
    private EditText etCreateCourse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Connect Activity to XML
        setContentView(R.layout.activity_create_student);


        // =====================================================
        // GET MANAGER
        // =====================================================

        manager = StudentManager.getInstance();


        // =====================================================
        // GET UI ELEMENTS
        // =====================================================

        etCreateName =
                findViewById(R.id.etCreateName);

        etCreateCourse =
                findViewById(R.id.etCreateCourse);

        Button btnCreateStudent =
                findViewById(R.id.btnCreateStudent);

        Button btnCancel =
                findViewById(R.id.btnCancel);


        // =====================================================
        // CREATE STUDENT
        // =====================================================

        btnCreateStudent.setOnClickListener(v -> {

            // Get input
            String name =
                    etCreateName
                            .getText()
                            .toString()
                            .trim();

            String course =
                    etCreateCourse
                            .getText()
                            .toString()
                            .trim();


            // =================================================
            // VALIDATION
            // =================================================

            if (name.isEmpty() ||
                    course.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please fill in all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }


            // =================================================
            // CREATE
            // =================================================

            manager.addStudent(
                    name,
                    course
            );


            // =================================================
            // SUCCESS
            // =================================================

            Toast.makeText(
                    this,
                    "Student created!",
                    Toast.LENGTH_SHORT
            ).show();


            // Go back to MainActivity
            finish();
        });


        // =====================================================
        // CANCEL
        // =====================================================

        btnCancel.setOnClickListener(v -> {

            // Do not create anything
            finish();
        });
    }
}
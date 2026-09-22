package com.example.codefest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codefest.students.Student;
import com.example.codefest.students.StudentManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // =====================================================
    // VARIABLES
    // =====================================================

    // StudentManager handles our student data
    private StudentManager manager;

    // Container where student cards will be displayed
    private LinearLayout studentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Connect this Activity to activity_main.xml
        setContentView(R.layout.activity_main);


        // =====================================================
        // GET MANAGER
        // =====================================================

        manager = StudentManager.getInstance();


        // =====================================================
        // GET UI ELEMENTS
        // =====================================================

        studentContainer =
                findViewById(R.id.studentContainer);

        Button btnOpenCreate =
                findViewById(R.id.btnOpenCreate);


        // =====================================================
        // CREATE BUTTON
        // =====================================================

        btnOpenCreate.setOnClickListener(v -> {

            // Create Intent
            Intent intent =
                    new Intent(
                            MainActivity.this,
                            CreateStudentActivity.class
                    );

            // Open CreateStudentActivity
            startActivity(intent);
        });


        // =====================================================
        // DISPLAY STUDENTS
        // =====================================================

        displayStudent(
                manager.getAllStudent()
        );
    }


    // =========================================================
    // REFRESH STUDENT LIST
    // =========================================================

    @Override
    protected void onResume() {

        super.onResume();

        // Refresh the student list when returning
        // from Create or Edit page
        if (manager != null &&
                studentContainer != null) {

            displayStudent(
                    manager.getAllStudent()
            );
        }
    }


    // =========================================================
    // DISPLAY STUDENTS
    // =========================================================

    private void displayStudent(
            List<Student> students
    ) {

        // Remove old cards first
        studentContainer.removeAllViews();


        // Loop through every student
        for (Student student : students) {


            // =================================================
            // CREATE STUDENT CARD
            // =================================================

            View studentItem =
                    getLayoutInflater().inflate(
                            R.layout.student_item,
                            studentContainer,
                            false
                    );


            // =================================================
            // GET TEXTVIEWS
            // =================================================

            TextView tvDisplayName =
                    studentItem.findViewById(
                            R.id.tvDisplayName
                    );

            TextView tvDisplayCourse =
                    studentItem.findViewById(
                            R.id.tvDisplayCourse
                    );


            // =================================================
            // GET BUTTONS
            // =================================================

            Button btnView =
                    studentItem.findViewById(
                            R.id.btnView
                    );

            Button btnEdit =
                    studentItem.findViewById(
                            R.id.btnEdit
                    );


            // =================================================
            // DISPLAY STUDENT INFORMATION
            // =================================================

            tvDisplayName.setText(
                    student.getName()
            );

            tvDisplayCourse.setText(
                    student.getCourse()
            );


            // =================================================
            // VIEW BUTTON
            // =================================================

            btnView.setOnClickListener(v -> {

                // Create Intent
                Intent intent =
                        new Intent(
                                MainActivity.this,
                                ViewStudentActivity.class
                        );


                // Send student ID
                intent.putExtra(
                        "student_id",
                        student.getId()
                );


                // Open ViewStudentActivity
                startActivity(intent);
            });


            // =================================================
            // EDIT BUTTON
            // =================================================

            btnEdit.setOnClickListener(v -> {

                // Create Intent
                Intent intent =
                        new Intent(
                                MainActivity.this,
                                EditStudentActivity.class
                        );


                // Send student ID
                intent.putExtra(
                        "student_id",
                        student.getId()
                );


                // Open EditStudentActivity
                startActivity(intent);
            });


            // =================================================
            // ADD CARD TO SCREEN
            // =================================================

            studentContainer.addView(
                    studentItem
            );
        }
    }
}


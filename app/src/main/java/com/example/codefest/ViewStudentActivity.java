package com.example.codefest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codefest.students.Student;
import com.example.codefest.students.StudentManager;

import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {

    // StudentManager
    private StudentManager manager;

    // ID of the student we want to view
    private int studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Connect Activity to XML
        setContentView(R.layout.activity_view_student);


        // =====================================================
        // GET MANAGER
        // =====================================================

        manager = StudentManager.getInstance();


        // =====================================================
        // GET UI ELEMENTS
        // =====================================================

        TextView tvViewId =
                findViewById(R.id.tvViewId);

        TextView tvViewName =
                findViewById(R.id.tvViewName);

        TextView tvViewCourse =
                findViewById(R.id.tvViewCourse);

        Button btnBack =
                findViewById(R.id.btnBack);


        // =====================================================
        // GET STUDENT ID
        // =====================================================

        studentId =
                getIntent().getIntExtra(
                        "student_id",
                        -1
                );


        // =====================================================
        // FIND STUDENT
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
        // DISPLAY STUDENT INFORMATION
        // =====================================================

        if (selectedStudent != null) {

            tvViewId.setText(
                    String.valueOf(
                            selectedStudent.getId()
                    )
            );

            tvViewName.setText(
                    selectedStudent.getName()
            );

            tvViewCourse.setText(
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
        // BACK BUTTON
        // =====================================================

        btnBack.setOnClickListener(v -> {

            finish();

        });
    }
}
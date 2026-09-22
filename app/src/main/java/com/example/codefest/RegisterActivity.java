package com.example.codefest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.codefest.users.UserManager;

public class RegisterActivity extends AppCompatActivity {

    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        manager = UserManager.getInstance();

        EditText etRegName = findViewById(R.id.etRegName);
        EditText etRegEmail = findViewById(R.id.etRegEmail);
        EditText etRegPassword = findViewById(R.id.etRegPassword);
        Button RegisterBtn = findViewById(R.id.RegisterBtn);


        RegisterBtn.setOnClickListener(v -> {
            String name = etRegName.getText().toString().trim();
            String email = etRegEmail.getText().toString().trim();
            String password = etRegPassword.getText().toString().trim();

            if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT);
                return;
            }

            if(!email.contains("@")){
                Toast.makeText(this, "Please put @ in Email", Toast.LENGTH_SHORT);
                return;
            }



        });




    }
}
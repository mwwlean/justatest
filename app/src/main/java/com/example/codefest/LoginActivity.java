package com.example.codefest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.codefest.users.User;
import com.example.codefest.users.UserManager;

public class LoginActivity extends AppCompatActivity {

    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        manager = UserManager.getInstance();

        EditText etLogEmail = findViewById(R.id.etLogEmail);
        EditText etLogPassword = findViewById(R.id.etLogPassword);
        Button LoginBtn = findViewById(R.id.LoginBtn);

        LoginBtn.setOnClickListener(v -> {
            String email = etLogEmail.getText().toString().trim();
            String password = etLogPassword.getText().toString().trim();

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Fill this field", Toast.LENGTH_SHORT);
                return;
            }

            User user = manager.loginUser(email, password);

            if(user != null){
                Toast.makeText(this, "Welcome" + user.getName(), Toast.LENGTH_SHORT);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "invalid credentials", Toast.LENGTH_SHORT);
            }
        });




    }
}
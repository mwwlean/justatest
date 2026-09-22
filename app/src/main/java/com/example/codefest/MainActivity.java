package com.example.codefest;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codefest.users.UserManager;

public class MainActivity extends AppCompatActivity {

    private UserManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etRegName = findViewById(R.id.etRegName);
        String name = etRegName.getText().toString().trim();




    }
}
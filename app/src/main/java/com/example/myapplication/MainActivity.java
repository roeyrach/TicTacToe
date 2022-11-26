package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.main_addstudent_btn);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStartActivity.class);
            startActivity(intent);
        });

    }
}
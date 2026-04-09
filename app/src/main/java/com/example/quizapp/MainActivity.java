package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private Button btnStart;
    private ImageButton btnThemeToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.applyThemeFromPrefs(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnStart = findViewById(R.id.btnStart);
        btnThemeToggle = findViewById(R.id.btnThemeToggle);

        // Pre-fill name if returning from ResultActivity via "Take New Quiz"
        String returnedName = getIntent().getStringExtra("USER_NAME");
        if (returnedName != null && !returnedName.isEmpty()) {
            etName.setText(returnedName);
        }

        btnStart.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("USER_NAME", name);
            startActivity(intent);
        });

        btnThemeToggle.setOnClickListener(v -> {
            ThemeManager.toggleAndSave(this);
            recreate();
        });
    }
}

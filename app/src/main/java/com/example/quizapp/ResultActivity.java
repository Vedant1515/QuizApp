package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.applyThemeFromPrefs(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String userName = getIntent().getStringExtra("USER_NAME");
        if (userName == null) userName = "User";
        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 10);

        TextView tvCongrats = findViewById(R.id.tvCongrats);
        TextView tvScore = findViewById(R.id.tvScore);
        Button btnNewQuiz = findViewById(R.id.btnNewQuiz);
        Button btnFinish = findViewById(R.id.btnFinish);
        ImageButton btnThemeToggle = findViewById(R.id.btnThemeToggle);

        tvCongrats.setText("Congratulations " + userName + "!");
        tvScore.setText(score + "/" + total);

        final String finalUserName = userName;
        btnNewQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("USER_NAME", finalUserName);
            startActivity(intent);
            finish();
        });

        btnFinish.setOnClickListener(v -> finishAffinity());

        btnThemeToggle.setOnClickListener(v -> {
            ThemeManager.toggleAndSave(this);
            recreate();
        });
    }
}

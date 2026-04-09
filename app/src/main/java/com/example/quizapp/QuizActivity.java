package com.example.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView tvProgress;
    private TextView tvGreeting;
    private TextView tvQuestionLabel;
    private TextView tvQuestion;
    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnSubmit;
    private ImageButton btnThemeToggle;

    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;
    private int selectedIndex = -1;
    private boolean submitted = false;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeManager.applyThemeFromPrefs(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        userName = getIntent().getStringExtra("USER_NAME");
        if (userName == null) userName = "User";

        questions = QuizData.getQuestions();

        progressBar = findViewById(R.id.progressBar);
        tvProgress = findViewById(R.id.tvProgress);
        tvGreeting = findViewById(R.id.tvGreeting);
        tvQuestionLabel = findViewById(R.id.tvQuestionLabel);
        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnThemeToggle = findViewById(R.id.btnThemeToggle);

        // Restore state if recreating (e.g. after theme toggle)
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0);
            score = savedInstanceState.getInt("score", 0);
            selectedIndex = savedInstanceState.getInt("selectedIndex", -1);
            submitted = savedInstanceState.getBoolean("submitted", false);
        }

        progressBar.setMax(questions.size());
        progressBar.setProgress(currentIndex);
        tvGreeting.setText("Welcome " + userName + "!");

        List<Button> optionButtons = Arrays.asList(btnOption1, btnOption2, btnOption3);

        loadQuestion();

        // Restore visual state after recreate
        if (submitted) {
            applyAnswerColors(optionButtons);
            for (Button btn : optionButtons) btn.setEnabled(false);
            btnSubmit.setText("NEXT");
        } else if (selectedIndex != -1) {
            highlightSelected(optionButtons, selectedIndex);
        }

        for (int i = 0; i < optionButtons.size(); i++) {
            final int index = i;
            optionButtons.get(i).setOnClickListener(v -> {
                if (!submitted) {
                    selectedIndex = index;
                    highlightSelected(optionButtons, index);
                }
            });
        }

        btnSubmit.setOnClickListener(v -> {
            if (!submitted) {
                if (selectedIndex == -1) return;
                submitted = true;
                checkAnswer(optionButtons);
                btnSubmit.setText("NEXT");
            } else {
                currentIndex++;
                progressBar.setProgress(currentIndex);
                if (currentIndex >= questions.size()) {
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("USER_NAME", userName);
                    intent.putExtra("SCORE", score);
                    intent.putExtra("TOTAL", questions.size());
                    startActivity(intent);
                    finish();
                } else {
                    selectedIndex = -1;
                    submitted = false;
                    loadQuestion();
                    resetButtons(optionButtons);
                    btnSubmit.setText("SUBMIT");
                }
            }
        });

        btnThemeToggle.setOnClickListener(v -> {
            ThemeManager.toggleAndSave(this);
            recreate();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", currentIndex);
        outState.putInt("score", score);
        outState.putInt("selectedIndex", selectedIndex);
        outState.putBoolean("submitted", submitted);
    }

    private void loadQuestion() {
        Question question = questions.get(currentIndex);
        tvProgress.setText((currentIndex + 1) + "/" + questions.size());
        tvQuestion.setText(question.getQuestionText());
        btnOption1.setText(question.getOptions().get(0));
        btnOption2.setText(question.getOptions().get(1));
        btnOption3.setText(question.getOptions().get(2));
    }

    private void highlightSelected(List<Button> buttons, int selectedIdx) {
        int defaultColor = ContextCompat.getColor(this, R.color.option_default);
        int defaultText = ContextCompat.getColor(this, R.color.option_text);
        int selectedBg = ContextCompat.getColor(this, R.color.selected_blue);
        int white = ContextCompat.getColor(this, android.R.color.white);

        for (int i = 0; i < buttons.size(); i++) {
            if (i == selectedIdx) {
                buttons.get(i).setBackgroundTintList(ColorStateList.valueOf(selectedBg));
                buttons.get(i).setTextColor(white);
            } else {
                buttons.get(i).setBackgroundTintList(ColorStateList.valueOf(defaultColor));
                buttons.get(i).setTextColor(defaultText);
            }
        }
    }

    private void checkAnswer(List<Button> buttons) {
        for (Button btn : buttons) btn.setEnabled(false);
        if (selectedIndex == questions.get(currentIndex).getCorrectIndex()) score++;
        applyAnswerColors(buttons);
    }

    private void applyAnswerColors(List<Button> buttons) {
        int correct = questions.get(currentIndex).getCorrectIndex();
        int white = ContextCompat.getColor(this, android.R.color.white);

        resetButtons(buttons);

        if (selectedIndex != correct) {
            buttons.get(selectedIndex).setBackgroundTintList(
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.wrong_red)));
            buttons.get(selectedIndex).setTextColor(white);
        }
        buttons.get(correct).setBackgroundTintList(
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.correct_green)));
        buttons.get(correct).setTextColor(white);
    }

    private void resetButtons(List<Button> buttons) {
        int defaultColor = ContextCompat.getColor(this, R.color.option_default);
        int defaultText = ContextCompat.getColor(this, R.color.option_text);
        for (Button btn : buttons) {
            btn.setEnabled(true);
            btn.setBackgroundTintList(ColorStateList.valueOf(defaultColor));
            btn.setTextColor(defaultText);
        }
    }
}

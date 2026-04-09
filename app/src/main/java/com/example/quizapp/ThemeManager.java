package com.example.quizapp;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class ThemeManager {

    private static final String PREFS_NAME = "quiz_prefs";
    private static final String KEY_DARK_MODE = "dark_mode";

    public static boolean isDarkMode(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getBoolean(KEY_DARK_MODE, false);
    }

    public static void applyThemeFromPrefs(Context context) {
        boolean darkMode = isDarkMode(context);
        AppCompatDelegate.setDefaultNightMode(
                darkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }

    public static void toggleAndSave(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean newDarkMode = !prefs.getBoolean(KEY_DARK_MODE, false);
        prefs.edit().putBoolean(KEY_DARK_MODE, newDarkMode).apply();
        AppCompatDelegate.setDefaultNightMode(
                newDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }
}

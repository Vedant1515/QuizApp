# SIT708 Quiz App

A native Android quiz application built for SIT708 - Mobile Application Development.

## Overview

The app presents a 10-question multiple choice quiz on computing and technology topics. It supports light and dark mode with persistent theme preference, and tracks the user's score across all questions.

## Features

- Name entry screen with input validation
- 10 multiple choice questions with 3 options each
- Progress bar showing quiz completion
- Correct answer highlighted green, wrong answer highlighted red after submission
- Dark and light mode toggle available on every screen
- Theme preference saved across sessions using SharedPreferences
- Score summary screen with option to retake the quiz or exit

## Screens

**MainActivity** - Accepts the user's name before starting the quiz. If the user returns from the result screen via Take New Quiz, their name is pre-filled automatically.

**QuizActivity** - Displays one question at a time with three answer buttons. The user selects an answer and presses Submit to see the result. The Submit button becomes Next after submission. After the last question, the app navigates to the result screen.

**ResultActivity** - Shows the user's final score and a congratulations message. Provides two options: Take New Quiz returns to the main screen, and Finish closes the application entirely.

## Tech Stack

- Language: Java
- Minimum SDK: 24
- Target SDK: 34
- Material Components for Android
- AppCompatDelegate for day/night mode switching

## Project Structure

```
app/src/main/java/com/example/quizapp/
    MainActivity.java       - Name entry and navigation
    QuizActivity.java       - Quiz logic, answer checking, progress tracking
    ResultActivity.java     - Score display and navigation
    ThemeManager.java       - Dark/light mode toggle and SharedPreferences
    Question.java           - Data model for a single question
    QuizData.java           - List of 10 quiz questions

app/src/main/res/
    layout/                 - XML layouts for all three screens
    values/                 - Light mode colours and theme
    values-night/           - Dark mode colour overrides
    drawable/               - Theme toggle icon
```

## Topics Covered in Quiz

- CPU and RAM definitions
- Android development language
- HTTP protocol
- Operating systems
- Artificial intelligence
- Google and Android history
- APIs
- Data structures (Stack)
- Large Language Models

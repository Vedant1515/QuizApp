package com.example.quizapp;

import java.util.Arrays;
import java.util.List;

public class QuizData {

    private static final List<Question> questions = Arrays.asList(
            new Question(
                    "What does CPU stand for?",
                    Arrays.asList("Central Processing Unit", "Computer Personal Unit", "Core Processing Utility"),
                    0
            ),
            new Question(
                    "Which programming language is officially preferred for Android development by Google?",
                    Arrays.asList("Java", "Swift", "Kotlin"),
                    2
            ),
            new Question(
                    "What does RAM stand for?",
                    Arrays.asList("Read Access Memory", "Random Access Memory", "Rapid Access Module"),
                    1
            ),
            new Question(
                    "What does HTTP stand for?",
                    Arrays.asList("HyperText Transfer Protocol", "High Transfer Text Process", "Hyper Terminal Text Program"),
                    0
            ),
            new Question(
                    "Which of the following is an Operating System?",
                    Arrays.asList("Python", "Linux", "MongoDB"),
                    1
            ),
            new Question(
                    "What does AI stand for in computing?",
                    Arrays.asList("Automated Interface", "Artificial Intelligence", "Advanced Integration"),
                    1
            ),
            new Question(
                    "Which company developed the Android operating system?",
                    Arrays.asList("Apple", "Microsoft", "Google"),
                    2
            ),
            new Question(
                    "What does API stand for?",
                    Arrays.asList("Application Programming Interface", "Automated Program Integration", "Advanced Processing Input"),
                    0
            ),
            new Question(
                    "Which data structure uses LIFO (Last In, First Out) order?",
                    Arrays.asList("Queue", "Stack", "Linked List"),
                    1
            ),
            new Question(
                    "What does LLM stand for in the context of AI?",
                    Arrays.asList("Low Level Machine", "Large Language Model", "Logical Learning Module"),
                    1
            )
    );

    public static List<Question> getQuestions() {
        return questions;
    }
}

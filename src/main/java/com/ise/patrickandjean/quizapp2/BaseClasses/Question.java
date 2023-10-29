package com.ise.patrickandjean.quizapp2.BaseClasses;

import com.ise.patrickandjean.quizapp2.Services.UtilityService;

public class Question {
    String text;
    String[] answers;
    String difficulty;
    String category;

    public Question(String question, String[] answers, String difficulty, String category) {
        this.text = question;
        this.answers = answers;
        this.difficulty = difficulty;
        this.category = category;
    }

    public String getQuestionText() {
        return text;
    }

    public String[] getOrderedAnswers() {
        return answers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }
}

package com.ise.patrickandjean.quizapp2.BaseClasses;

import com.ise.patrickandjean.quizapp2.Services.UtilityService;

public class Question {
    //All of the characteristics a Question object must have
    String text;
    String[] answers;
    Difficulty difficulty;
    String category;

    //Constructor for Question class
    public Question(String question, String[] answers, Difficulty difficulty, String category) {
        this.text = question;
        this.answers = answers;
        this.difficulty = difficulty;
        this.category = category;
    }


    //Getter functions
    public String getQuestionText() {
        return text;
    }

    public String[] getOrderedAnswers() {
        return answers;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }
}

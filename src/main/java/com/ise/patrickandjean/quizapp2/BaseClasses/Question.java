package com.ise.patrickandjean.quizapp2.BaseClasses;

public class Question {
    //Each instance of the Question class has these 4 properties
    private final String question;
    private final String[] answers;
    private final String difficulty;
    private final String category;
    private static final String[] difficultyLevels = {"Novice", "Intermediate", "Expert"};


    /**
     *constructor
     */
    public Question(String question, String[] answers, String difficulty, String category) {
        this.question = question;
        this.answers = answers;
        this.difficulty = difficulty;
        this.category = category;
    }

    //getter functions
    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }

    //static because it has to do with the class, not each instance of the class
    public static String[] getDifficultyLevels() {return difficultyLevels;}
}

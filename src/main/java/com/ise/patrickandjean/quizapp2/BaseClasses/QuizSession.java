package com.ise.patrickandjean.quizapp2.BaseClasses;

public class QuizSession {
    boolean active;
    long gameStartEpoch;
    int currentQuestionNumber;
    int maxQuestions;
    int score;
    Question currentQuestion;

    public QuizSession(int maxQuestions) {
        this.active = true;
        this.gameStartEpoch = System.currentTimeMillis();

        this.maxQuestions = maxQuestions;
        this.currentQuestionNumber = 0;
    }

    /// === Score ===
    public int getScore() {
        return this.score;
    }

    public void incrementScore() {
        this.score += 1;
    }

    /// === Question ===
    public Question getCurrentQuestion() {
        return this.currentQuestion;
    }

    public void setCurrentQuestion(Question question) {
        this.currentQuestion = question;
    }

    public int getCurrentQuestionNumber() {
        return this.currentQuestionNumber;
    }

    public int getMaxQuestionCount() {
        return this.maxQuestions;
    }

    public void incrementCurrentQuestion() {
        if (this.currentQuestionNumber + 1 > this.maxQuestions) {
            throw new IllegalStateException("CurrentQuestion incrementation would exceed max questions!");
        }

        this.currentQuestionNumber += 1;
    }

    /// === Misc ===
    public void endSession() {
        this.active = false;
    }

    public long getStartEpoch() {
        return this.gameStartEpoch;
    }

    public boolean isActive() {
        return this.active;
    }

}

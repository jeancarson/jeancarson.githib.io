package com.ise.patrickandjean.quizapp2.GameModes;

import com.ise.patrickandjean.quizapp2.BaseClasses.Question;
import com.ise.patrickandjean.quizapp2.BaseClasses.QuestionBank;
import com.ise.patrickandjean.quizapp2.Pages.QuestionAsker.QuestionAskerController;
import com.ise.patrickandjean.quizapp2.Services.UIService;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class IncreasingDifficulty extends GameMode {
    public final static int TOTAL_QUESTIONS = 6;
    @Override
    public void run() {
        /// Get questions that we'll ask
        QuestionBank QB = new QuestionBank();
        ArrayList<Question> questionsToAsk = new ArrayList<>();
        questionsToAsk.addAll(QB.popQuestionByDifficulty("Novice", 2));
        questionsToAsk.addAll(QB.popQuestionByDifficulty("Intermediate", 2));
        questionsToAsk.addAll(QB.popQuestionByDifficulty("Expert", 2));

        /// Start Game loop
        Task<Void> gameTask = new Task<Void>() {
            @Override
            protected Void call() {
                /// Start a new quiz session
                QuestionAskerController questionAskerController = (QuestionAskerController) UIService.getController("QuestionAsker");
                questionAskerController.startNewQuizSession(TOTAL_QUESTIONS, "increasingDifficultyGameHistory");

                /// Keep asking while we have more questions
                while (!questionsToAsk.isEmpty()) {
                    /// Shift a question off the front of the list
                    Question nextQuestion = questionsToAsk.remove(0);

                    /// This yields until the user chooses an answer
                    questionAskerController.nextQuestion(nextQuestion);
                }

                /// All done! :D
                questionAskerController.finishQuizSessionAndShowStats("QsOver");
                return null;
            }
        };
        new Thread(gameTask).start();
    }
}

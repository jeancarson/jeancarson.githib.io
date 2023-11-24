package com.ise.patrickandjean.quizapp2.GameModes;

import com.ise.patrickandjean.quizapp2.BaseClasses.Question;
import com.ise.patrickandjean.quizapp2.BaseClasses.QBank;
import com.ise.patrickandjean.quizapp2.Pages.QuestionAsker.QuestionAskerController;
import com.ise.patrickandjean.quizapp2.Services.UIService;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class Elimination extends GameMode {
    public static int MAX_QUESTIONS = 18;
    public static String SAVE_FILE_INDEX = "eliminationGameHistory";
    @Override
    public void run() {
        /// Get questions that we'll ask
        QBank QB = new QBank();
        ArrayList<Question> questionsToAsk = QB.popQuestionRandom(MAX_QUESTIONS);

        /// Start Game loop
        Task<Void> gameTask = new Task<Void>() {
            @Override
            protected Void call() {
                /// Start a new quiz session
                QuestionAskerController questionAskerController = (QuestionAskerController) UIService.getController("QuestionAsker");
                questionAskerController.startNewQuizSession(MAX_QUESTIONS, "eliminationGameHistory");

                /// Keep asking while we have more questions
                while (!questionsToAsk.isEmpty()) {
                    /// Shift a question off the front of the list
                    Question nextQuestion = questionsToAsk.remove(0);

                    /// This yields until the user chooses an answer
                    boolean wasCorrect = questionAskerController.nextQuestion(nextQuestion);

                    /// They were wrong! Eliminated!
                    if (!wasCorrect) break;
                }

                /// All done! :D
                questionAskerController.finishQuizSessionAndShowStats("QsOver");
                return null;
            }
        };
        new Thread(gameTask).start();
    }
}

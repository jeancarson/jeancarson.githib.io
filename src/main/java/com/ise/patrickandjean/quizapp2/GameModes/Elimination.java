package com.ise.patrickandjean.quizapp2.GameModes;

import com.ise.patrickandjean.quizapp2.BaseClasses.Question;
import com.ise.patrickandjean.quizapp2.BaseClasses.QuestionBank;
import com.ise.patrickandjean.quizapp2.Pages.QuestionAsker.QuestionAskerController;
import com.ise.patrickandjean.quizapp2.Services.UIService;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class Elimination {
//    public static void runEliminationMode() {
//        QuestionBank QB = new QuestionBank();
//        boolean anotherRound = true;
//        int counter = 0;   //this counter allows us to end the game if the user answers all possible questions correctly
//        while (anotherRound && counter < 18) {
//            //anotherRound = cont.SetUIQuestion(QB.get(counter));
//            if (anotherRound) {
//                counter++;
//            } else {
//                //System.out.println("You're out!");
//                break;
//            }
//        }
//        if (counter == 18) {
//            //System.out.println("Congrats!!! You got all the questions right");}
//
//        }
//    }
    private final static int TOTAL_QUESTIONS = 18;

    public static void run() {
        /// Get questions that we'll ask
        QuestionBank QB = new QuestionBank();
        ArrayList<Question> questionsToAsk = QB.popQuestionRandom(TOTAL_QUESTIONS);

        /// Start Game loop
        Task<Void> gameTask = new Task<Void>() {
            @Override
            protected Void call() {
                /// Start a new quiz session
                QuestionAskerController questionAskerController = (QuestionAskerController) UIService.getController("QuestionAsker");
                questionAskerController.startNewQuizSession(TOTAL_QUESTIONS);

                /// Keep asking while we have more questions
                while (!questionsToAsk.isEmpty()) {
                    /// Shift a question off the front of the list
                    Question nextQuestion = questionsToAsk.remove(0);

                    /// This yields until the user chooses an answer
                    questionAskerController.nextQuestion(nextQuestion);
                }

                /// All done! :D
                questionAskerController.finishQuizSessionAndShowStats();
                return null;
            }
        };
        new Thread(gameTask).start();
    }
}

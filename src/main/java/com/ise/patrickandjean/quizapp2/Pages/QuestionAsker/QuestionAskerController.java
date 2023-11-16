package com.ise.patrickandjean.quizapp2.Pages.QuestionAsker;

import com.ise.patrickandjean.quizapp2.BaseClasses.QuizSession;
import com.ise.patrickandjean.quizapp2.BaseClasses.Question;
import com.ise.patrickandjean.quizapp2.Pages.EndGamePages.QsOver;
import com.ise.patrickandjean.quizapp2.Services.SaveService;
import com.ise.patrickandjean.quizapp2.Services.UIService;


import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class QuestionAskerController {
    @FXML
    public Button btnA;
    @FXML
    public Button btnB;
    @FXML
    public Button btnC;
    @FXML
    public Button btnD;
    @FXML
    public Label timertext;
    @FXML
    public Label currentquestiontext;
    @FXML
    public Label questiondisplay;

    /// Private
    private QuizSession currentSession;
    private CompletableFuture<Boolean> answerChosenSignal;

    private boolean answerButtonDebounce = false;

    /// Funcs
    private void startTimerUIUpdateLoop() {
        Executors.newSingleThreadExecutor().execute(() -> {
            Duration duration = Duration.ofSeconds((long) (60));
            while (duration.compareTo(Duration.ZERO) > 0) {
                /// Game over - don't need to update timer anymore
                if (currentSession == null || !currentSession.isActive()) {
                    break;
                }

                /// Vars
                double secondsSinceQuizStart = Math.floor((double) (System.currentTimeMillis() - currentSession.getStartEpoch()) / 1000);
                duration = Duration.ofSeconds((long) (4- secondsSinceQuizStart));

                /// Update timer text
                /// Must be in a run later to avoid "Not on FX application thread" error
                /// Because you can only edit scene objects at runtime from their thread?!
                String displayText = String.format("%02d:%02d", duration.toMinutesPart(), duration.toSecondsPart());
                Platform.runLater(() -> {
                    timertext.setText(displayText);
                });

                /// Rest
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //break to end of game screen.
            Platform.runLater(this::finishQuizSessionAndShowStats);
        });
    }

    private void updateQuestionCounter() {
        String displayQuestionText = String.format("%s/%s", currentSession.getCurrentQuestionNumber(), currentSession.getMaxQuestionCount());
        Platform.runLater(() -> {
            currentquestiontext.setText(displayQuestionText);
        });
    }

    private void updateQuestionDisplay(Question question) {
        Platform.runLater(() -> {
            /// Set question text
            questiondisplay.setText(question.getQuestionText());

            /// Shuffle ordered answers (create new ArrayList, so we don't modify the OG question)
            List<String> shuffledAnswers = new ArrayList<>(Arrays.asList(question.getOrderedAnswers()));
            Collections.shuffle(shuffledAnswers);

            /// Reset button colors to default
            for (Button btn : new Button[]{btnA, btnB, btnC, btnD}) {
                btn.setStyle("-fx-background-color: #FFFFFF");
            }

            /// Update button text
            btnA.setText(shuffledAnswers.get(0));
            btnB.setText(shuffledAnswers.get(1));
            btnC.setText(shuffledAnswers.get(2));
            btnD.setText(shuffledAnswers.get(3));
        });
    }

    public void startNewQuizSession(int MAX_QUESTIONS, String SAVE_FILE_INDEX) {
        /// Create a new session, and store it in the quizaskercontroller for now
        currentSession = new QuizSession(MAX_QUESTIONS, SAVE_FILE_INDEX);

        /// Start timer update loop
        startTimerUIUpdateLoop();
    }

    public boolean nextQuestion(Question question) {
        /// Increment internal counter
        currentSession.incrementCurrentQuestion();
        updateQuestionCounter();

        /// Update session
        currentSession.setCurrentQuestion(question);

        /// Display new question text
        updateQuestionDisplay(question);

        /// Wait for an answer
        answerChosenSignal = new CompletableFuture<>();
        try {
            return answerChosenSignal.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void finishQuizSessionAndShowStats() {
        /// Clear current session
        QuizSession savedSessionData = currentSession;
        currentSession = null;

        /// Save game result to file
        SaveService.addNewGameResult(savedSessionData.getSaveFileIndex(), savedSessionData.getScore());

        /// Show stats!
        try {
            /// Show stat scene
            UIService.setActiveScene("Stats");

            /// Update scene values
            QsOver qsOver = (QsOver) UIService.getController("Stats");
            qsOver.setViewWithSessionData(savedSessionData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void answerSelected(ActionEvent event) {
        /// Sanity
        if (currentSession == null) return;

        /// Button is busy
        if (answerButtonDebounce) return;
        answerButtonDebounce = true;

        /// Get pressed button
        Button buttonPressed = (Button) event.getSource();
        String selectedAnswer = buttonPressed.getText();

        /// Get current questions
        Question currentQuestion = currentSession.getCurrentQuestion();
        String correctAnswer = currentQuestion.getOrderedAnswers()[0];

        /// Was it right?
        boolean selectedCorrectButton = (selectedAnswer.equals(correctAnswer));

        /// Handle highlights
        if (selectedCorrectButton) {
            /// Correct! -> Green
            buttonPressed.setStyle("-fx-background-color: #00FF00");

            /// Increase score!
            currentSession.incrementScore();
        } else {
            /// Wrong! -> Other Green, Other Red
            buttonPressed.setStyle("-fx-background-color: #FF0000");
            for (Button btn : new Button[]{btnA, btnB, btnC, btnD}) {
                if (!btn.getText().equals(correctAnswer)) continue;

                btn.setStyle("-fx-background-color: #00FF00");
                break;
            }
        }

        /// Question has been answered - resume the waiting thread
        /// after giving the person a second to admire the correct answers :D
        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
        pause.setOnFinished(e -> {
            answerButtonDebounce = false;
            answerChosenSignal.complete(selectedCorrectButton);
        });
        pause.play();
    }

    public void initialize() {
        UIService.Framework.addPointerFX(btnA);
        UIService.Framework.addPointerFX(btnB);
        UIService.Framework.addPointerFX(btnC);
        UIService.Framework.addPointerFX(btnD);
    }
}


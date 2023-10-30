package com.ise.patrickandjean.quizapp2.Pages.Stats;

import com.ise.patrickandjean.quizapp2.BaseClasses.QuizSession;
import com.ise.patrickandjean.quizapp2.Services.SaveService;
import com.ise.patrickandjean.quizapp2.Services.StatisticService;
import com.ise.patrickandjean.quizapp2.Services.UIService;
import com.ise.patrickandjean.quizapp2.Services.UtilityService;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class StatsController {
    /// Get all FXML objects
    @FXML
    private Text mainTitle;
    @FXML
    private Text scoreText;
    @FXML
    private Text myMeanVal;
    @FXML
    private Text myModeVal;
    @FXML
    private Text mySDVal;
    @FXML
    private Text globalMeanVal;
    @FXML
    private Text globalModeVal;
    @FXML
    private Text globalSDVal;

    private void updateScoreDisplay(int[] allScores, Text globalMeanVal, Text globalModeVal, Text globalSDVal) {
        double globalMean = StatisticService.calculateMean(allScores);
        globalMeanVal.setText(Double.toString(globalMean));

        int globalMode = StatisticService.calculateMode(allScores);
        globalModeVal.setText(Integer.toString(globalMode));

        double globalSD = StatisticService.calculateStandardDeviation(allScores);
        globalSDVal.setText(String.format("%.2f", globalSD));
    }

    public void setViewWithSessionData(QuizSession session) {
        /// Update main title
        mainTitle.setText(String.format("Well done, %s!", SaveService.getCurrentUser()));

        /// Set stats of recent game
        scoreText.setText(
                String.format(
                        "%s/%s — %s%%",
                        session.getScore(),
                        session.getMaxQuestionCount(),
                        Math.round(((double) session.getScore() / session.getMaxQuestionCount()) * 100)
                )
        );

        /// Set stats of all of this user's games of this game mode
        int[] currentUserScores = SaveService.getAllResultsForCurrentUserInGameMode(session.getSaveFileIndex());
        updateScoreDisplay(currentUserScores, myMeanVal, myModeVal, mySDVal);

        /// Set stats of all games ever across this game mode
        int[] allScores = SaveService.getAllResultsForGameMode(session.getSaveFileIndex());
        updateScoreDisplay(allScores, globalMeanVal, globalModeVal, globalSDVal);
    }

    public void returnHomeButtonPressed() {
        try {
            UIService.setActiveScene("DifficultyChooser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

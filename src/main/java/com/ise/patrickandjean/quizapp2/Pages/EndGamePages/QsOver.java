package com.ise.patrickandjean.quizapp2.Pages.EndGamePages;

import com.ise.patrickandjean.quizapp2.BaseClasses.QuizSession;
import com.ise.patrickandjean.quizapp2.Services.SaveService;
import com.ise.patrickandjean.quizapp2.Services.StatisticService;
import com.ise.patrickandjean.quizapp2.Services.UIService;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class QsOver extends EndGamePage {
    @Override
    public void setViewWithSessionData(QuizSession session) {
        /// Update main title
        mainTitle.setText(String.format("Well done, %s!", SaveService.getCurrentUser()));
        super.setViewWithSessionData(session);
    }
}

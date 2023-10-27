package com.ise.patrickandjean.quizapp2.Pages.QuestionAsker;

import com.ise.patrickandjean.quizapp2.Services.UIService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class QuestionAskerController {
    @FXML
    public Button btnA;
    @FXML
    public Button btnB;
    @FXML
    public Button btnC;
    @FXML
    public Button btnD;

    /// Vars
    boolean gameActive = false;
    long gameStartEpoch = 0;

    public void initNewQuizSession() {

    }

    public void endSession() {
        
    }

    public void initialize() {
        UIService.Framework.addPointerFX(btnA);
        UIService.Framework.addPointerFX(btnB);
        UIService.Framework.addPointerFX(btnC);
        UIService.Framework.addPointerFX(btnD);

    }
}


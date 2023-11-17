package com.ise.patrickandjean.quizapp2.Pages.DifficultyChooser;

import com.ise.patrickandjean.quizapp2.GameModes.Elimination;
import com.ise.patrickandjean.quizapp2.GameModes.GameMode;
import com.ise.patrickandjean.quizapp2.GameModes.IncreasingDifficulty;
import com.ise.patrickandjean.quizapp2.GameModes.RandomlyChosen;
import com.ise.patrickandjean.quizapp2.Services.SaveService;

import com.ise.patrickandjean.quizapp2.Services.UIService;
import com.ise.patrickandjean.quizapp2.Services.UtilityService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class DifficultyChooserController {
    @FXML
    public Button randomDrawButton;
    @FXML
    public Button increasingDifficultyButton;
    @FXML
    public Button eliminationButton;

    public void selectModeButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        /// Vars
        String buttonPressedName = ((Button) event.getSource()).getId();

        UIService.setActiveScene("QuestionAsker");

        // GameMode gm = new GameMode();cant do this bc gamemode is an abstract class and I'd like to keep it like that
        /// Perform correct action
        GameMode gm = null;
        switch(buttonPressedName){
            case "eliminationButton":
                gm = new Elimination();
                break;
            case "increasingDifficultyButton":
                gm = new IncreasingDifficulty();
                break;
            case "randomDrawButton":
                gm = new RandomlyChosen();
                break;
        }
        assert gm != null;
        gm.run();
    }

    public void initialize() {
        UIService.Framework.addPointerFX(randomDrawButton);
        UIService.Framework.addPointerFX(increasingDifficultyButton);
        UIService.Framework.addPointerFX(eliminationButton);
    }
}

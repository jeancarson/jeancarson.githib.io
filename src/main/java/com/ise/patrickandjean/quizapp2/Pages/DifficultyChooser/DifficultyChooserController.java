package com.ise.patrickandjean.quizapp2.Pages.DifficultyChooser;

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

        /// Perform correct action
        if (buttonPressedName.equals("eliminationButton")) {
            /// do elimination stuff
            return;
        }

        if (buttonPressedName.equals("increasingDifficultyButton")) {
            /// do increasing diff stuff
            return;
        }

        if (buttonPressedName.equals("randomDrawButton")) {
            RandomlyChosen.run();
            return;
        }
    }

    public void initialize() {
        UIService.Framework.addPointerFX(randomDrawButton);
        UIService.Framework.addPointerFX(increasingDifficultyButton);
        UIService.Framework.addPointerFX(eliminationButton);
    }
}

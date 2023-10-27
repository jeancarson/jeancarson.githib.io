package com.ise.patrickandjean.quizapp2.Pages.Login;

import com.ise.patrickandjean.quizapp2.Services.SaveService;

import com.ise.patrickandjean.quizapp2.Services.UIService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    private Button authButton;
    @FXML
    private StackPane allHolder;

    /// Internal Funcs
    private void displayError(String text) {
        authButtonBusy = true;
        /// Update color to red
        authButton.setStyle("-fx-background-color: #FF6666;");
        authButton.setText(text);

        PauseTransition resetTransition = new PauseTransition(Duration.seconds(1));
        resetTransition.setOnFinished(e -> {
            authButton.setStyle("-fx-background-color: #50C878;");
            updateSignInButtonText();
            authButtonBusy = false;
        });
        resetTransition.play();
    }

    private void moveToDifficultyChooserScene() throws IOException {
        UIService.setActiveScene("DifficultyChooser");
    }

    private void updateSignInButtonText() {
        String username = usernameField.getText().toLowerCase();

        if (SaveService.doesUserExist(username)) {
            authButton.setText("Sign In");
            return;
        }

        authButton.setText("Register");
    }

    /// Public Funcs
    private boolean authButtonBusy = false;

    public void authButtonPressed() throws IOException {
        if (authButtonBusy) return;

        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();

        if (username.isEmpty()) {
            displayError("Enter a Username!");
            return;
        }

        if (password.isEmpty()) {
            displayError("Enter a Password!");
            return;
        }

        /// No user exists - register them!
        final boolean userExists = SaveService.doesUserExist(username);
        if (!userExists) {
            SaveService.registerUser(username, password);
            moveToDifficultyChooserScene();
            return;
        }

        /// Try to log them in!
        if (!SaveService.loginUser(username, password)) {
            displayError("Invalid password!");
            return;
        }

        ///
        moveToDifficultyChooserScene();
    }

    public void initialize() {
        /// As someone enters a username - if it's valid we change "Register" to "Sign In"
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSignInButtonText();
        });

        /// Change mouse to pointer when hovering auth button
        UIService.Framework.addPointerFX(authButton);
    }
}
package com.ise.patrickandjean.quizapp2.Pages.Login;

import com.ise.patrickandjean.quizapp2.Services.SaveService;

import com.ise.patrickandjean.quizapp2.Services.UtilityService;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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
        /// Update color to red
        authButton.setStyle("-fx-background-color: #FF6666;");
        authButton.setText(text);

        PauseTransition resetTransition = new PauseTransition(Duration.seconds(1));
        resetTransition.setOnFinished(e -> {
            authButton.setStyle("-fx-background-color: #50C878;");
            authButton.setText("Sign In");
        });
        resetTransition.play();
    }

    private void moveToDifficultyChooserScene() {
        UtilityService.print("Request move to difficulty chooser scene");
    }

    /// Public Funcs
    public void authButtonPressed() {
        String username = usernameField.getText().toLowerCase();
        String password = passwordField.getText();

        if (username.isEmpty())
        {
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
            displayError("Invalid username or password!");
            return;
        }

        ///
        moveToDifficultyChooserScene();
    }
    public void initialize() {
        /// As someone enters a username - if it's valid we change "Register" to "Sign In"
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (SaveService.doesUserExist(newValue)) {
                authButton.setText("Sign In");
                return;
            }

            authButton.setText("Register");
        });

        /// Change mouse to pointer when hovering auth button
        authButton.setOnMouseEntered(e -> {
            allHolder.getScene().setCursor(Cursor.HAND);
        });

        authButton.setOnMouseExited(e -> {
            allHolder.getScene().setCursor(Cursor.DEFAULT);
        });
    }
}
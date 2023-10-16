package com.ise.patrickandjean.quizapp2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    private Button authButton;
    @FXML
    private Label alertBox;

    /// Internal Funcs
    private void displayError(String text) {
        alertBox.setText(text);
        alertBox.setVisible(true);
        alertBox.setManaged(true);
    }


    /// Public Funcs
    public void handleDoAuthButton() {
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());


        displayError("oops");
    }
}
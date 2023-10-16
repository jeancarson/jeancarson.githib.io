package com.ise.patrickandjean.quizapp2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException, NullPointerException {
        /// Get UI elements from FXML
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        /// Load scene + CSS
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("LoginStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        /// Set icon & title
        primaryStage.getIcons().add(new Image(getClass().getResource("Quizler.png").toString()));
        primaryStage.setTitle("Quizler — Login");

        /// Set minimum sizes
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);

        /// Display!
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
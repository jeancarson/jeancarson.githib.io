package com.ise.patrickandjean.quizapp2.Pages.DifficultyChooser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class DifficultyChooserApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException, NullPointerException {
        /// Get UI elements from FXML
        Parent root = FXMLLoader.load(getClass().getResource("DifficultyChooser.fxml"));

        /// Load scene + CSS
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("DifficultyChooserStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        /// Set icon & title
        primaryStage.getIcons().add(new Image(getClass().getResource("Quizler.png").toString()));
        primaryStage.setTitle("Quizler — Difficulty Chooser");

        /// Set minimum sizes
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);

        /// Display!
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}

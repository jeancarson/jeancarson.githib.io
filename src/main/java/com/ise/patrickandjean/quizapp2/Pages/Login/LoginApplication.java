package com.ise.patrickandjean.quizapp2.Pages.Login;

import com.ise.patrickandjean.quizapp2.Services.UIService;
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
        UIService.init(primaryStage);

        UIService.setActiveScene("Login");
        UIService.setTitle("Login");

        UIService.setVisible(true);
    }
}
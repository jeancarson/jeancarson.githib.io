package com.ise.patrickandjean.quizapp2.Services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

public class UIService {
    private static Stage primaryStage;
    private static final HashMap<String, Object> storedControllerReferences = new HashMap<>();

    private static URL getResource(String relativePath) throws MalformedURLException {
        String fullPath = UIService.class.getResource("").toString();
        String newPath = fullPath.replace("/Services/", "/Pages/") + relativePath;

        return new URL(newPath);
    }

    public static Object getController(String name) {
        return storedControllerReferences.get(name);
    }

    public static void setActiveScene(String name) throws IOException {
        /// Get UI elements + css
        FXMLLoader loader = new FXMLLoader(getResource("/" + name + "/" + name + ".fxml"));
        Parent root = loader.load();
        String resourcePath = getResource("/" + name + "/" + name + "Style.css").toExternalForm();

        /// Store controller for later external access
        storedControllerReferences.put(name, loader.getController());

        /// Update scene
        if (primaryStage.getScene() != null) {
            /// Set cursor to default
            primaryStage.getScene().setCursor(Cursor.DEFAULT);

            primaryStage.getScene().setRoot(root);
            primaryStage.getScene().getStylesheets().set(0, resourcePath);

            ///
            return;
        }

        /// First time round - we must set a scene
        Scene scene = new Scene(root);
        scene.getStylesheets().add(resourcePath);
        primaryStage.setScene(scene);
    }

    public static void init(Stage newStage) throws IOException {
        /// Update stored variable
        primaryStage = newStage;

        /// Set icon
        Image iconImage = new Image(getResource("Login/Quizler.png").toString());
        primaryStage.getIcons().add(iconImage);

        /// Set sizing constraints
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setMaximized(true);
    }

    public static void setVisible(boolean isVisible) {
        if (isVisible) {
            primaryStage.show();
            return;
        }

        primaryStage.hide();
    }

    public static void setTitle(String title) {
        primaryStage.setTitle("Quizler — " + title);
    }

    public static class Framework {
        public static void addPointerFX(Button button) {
            /// Change mouse to pointer when hovering auth button
            button.setOnMouseEntered(e -> {
                primaryStage.getScene().setCursor(Cursor.HAND);
            });

            button.setOnMouseExited(e -> {
                if (primaryStage.getScene() != null) {
                    primaryStage.getScene().setCursor(Cursor.DEFAULT);
                }
            });
        }
    }
}

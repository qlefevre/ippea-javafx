package com.financial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EquitySavingsPlanApp extends Application {


    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("application"));
        scene.getStylesheets().add(EquitySavingsPlanApp.class.getResource("styles.css").toExternalForm());
        stage.setTitle("IPPEA JavaFX");
        stage.setScene(scene);
        stage.getIcons().add(new Image(EquitySavingsPlanApp.class.getResourceAsStream("/icon.png")));
        stage.show();
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EquitySavingsPlanApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}

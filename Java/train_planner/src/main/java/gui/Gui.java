package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Gui class is main class of Application
 * open login.fxml
 */
public class Gui extends Application {

    private static Stage stg;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        stage.setResizable(false);
        stage.setTitle("TRAIN PLANNER");

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("search.fxml"));

        Scene scene = new Scene(root, 1400, 860);
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stg.getScene().setRoot(pane);
    }


    public static void main(String[] args) {
        launch();
    }
}
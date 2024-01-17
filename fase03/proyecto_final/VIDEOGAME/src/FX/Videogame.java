package FX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Videogame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        primaryStage.setTitle("Videojuego");
        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
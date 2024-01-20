package FX;

import FX.MainMenu.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class Videogame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga el archivo FXML del menú principal y configura la escena
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu/MainMenu.fxml"));
        Parent root = loader.load();

        MainMenuController controller = loader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package FX.MainMenu;

import Utils.Resolution;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainMenuController {
    private final ObservableList<Resolution> RESOLUTIONS = FXCollections.observableArrayList();
    
    @FXML
    private Button createMatchButton;
    @FXML
    private Button joinMatchButton;
    @FXML
    private TextField createMatchCode;
    @FXML
    private TextField joinMatchCode;
    @FXML
    private TextField nameInput;
    @FXML
    private Label playerName;
    @FXML
    private Label enemyName;
    @FXML
    private ComboBox<Resolution> resolutionInput;
    @FXML
    private Button startButton;

    public void initialize() {
        RESOLUTIONS.addAll(new Resolution(640, 480), new Resolution(1280, 720), new Resolution(1920, 1080));
        resolutionInput.setItems(RESOLUTIONS);
    }

    public void createMatch() {

    }
    
    public void joinMatch() {
        
    }

    public void setResolution() {
        
    }

    public void startMatch() {
        
    }

    public void setName() {
        
    }
}
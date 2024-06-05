package com.memory.Controllers.Game;

import com.memory.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameModeController implements Initializable {
    public Button easy_btn;
    public Button medium_btn;
    public Button hard_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        easy_btn.setOnAction(e -> onEasy());
    }

    private void onEasy() {
        // get stage
        Stage stage = (Stage) easy_btn.getScene().getWindow();
        // close stage the game mode window
        Model.getInstance().getViewFactory().closeStage(stage);
        // show game easy window
        Model.getInstance().getViewFactory().showGameEasyView();
    }
}

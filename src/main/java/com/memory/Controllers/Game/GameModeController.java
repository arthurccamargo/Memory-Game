package com.memory.Controllers.Game;

import com.memory.Models.Model;
import com.memory.Views.UserMenuItems;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
        Model.getInstance().getViewFactory().getUserMenuItems().set(UserMenuItems.GAME_EASY);
    }
}

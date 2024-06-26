package com.memory.Controllers.User;

import com.memory.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public BorderPane user_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getUserMenuItems().addListener((observableVal, oldVal, newVal) ->
        {
            // decide what will be in the center of the window
            switch (newVal) {
                case GAME -> user_parent.setCenter(Model.getInstance().getViewFactory().getGameModeView());
                case GAME_EASY -> user_parent.setCenter(Model.getInstance().getViewFactory().getGameEasyView());
                default -> user_parent.setCenter(Model.getInstance().getViewFactory().getHomeView());
            }
        });
    }
}

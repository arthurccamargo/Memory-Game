package com.memory.Controllers.User;

import com.memory.Models.Model;
import com.memory.Views.UserMenuItems;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {
    public Button home_btn;
    public Button game_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        home_btn.setOnAction(e -> onHome());
        logout_btn.setOnAction(e -> onLogout());
        game_btn.setOnAction(e -> onGame());
    }

    public void onHome() {
        Model.getInstance().getViewFactory().getUserMenuItems().set(UserMenuItems.HOME);
    }

    public void onGame() {Model.getInstance().getViewFactory().getUserMenuItems().set(UserMenuItems.GAME);}

    public void onLogout() {
        Stage stage = (Stage) home_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginView();
    }
}

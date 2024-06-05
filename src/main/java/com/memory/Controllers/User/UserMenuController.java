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
        // if you are in the easy game mode window and want to go to the home window
        if (Model.getInstance().getViewFactory().getUserMenuItems().get() == UserMenuItems.GAME_EASY) {
            // show alert
           Model.getInstance().getViewFactory().showAlert(UserMenuItems.HOME);
        } else {
            Model.getInstance().getViewFactory().getUserMenuItems().set(UserMenuItems.HOME);
        }
    }

    public void onGame() {
        // if you are in the easy game mode window and want to go to the game mode window
        if (Model.getInstance().getViewFactory().getUserMenuItems().get() == UserMenuItems.GAME_EASY) {
            Model.getInstance().getViewFactory().showAlert(UserMenuItems.GAME);
        } else {
            Model.getInstance().getViewFactory().getUserMenuItems().set(UserMenuItems.GAME);
        }
    }

    public void onLogout() {
        // if you are anywhere and want to go out
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().showAlertLogout(stage);
    }
}

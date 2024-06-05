package com.memory.Controllers.User;

import com.memory.Models.Model;
import com.memory.Views.UserMenuItems;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {
    public Button home_btn;
    public Button game_btn;
    public Button logout_btn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    public void addListeners() {
        home_btn.setOnAction(e -> onHome());
    }

    public void onHome() {
        Model.getInstance().getViewFactory().getUserMenuItems().set(UserMenuItems.HOME);
    }
}

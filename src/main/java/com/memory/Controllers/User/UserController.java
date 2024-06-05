package com.memory.Controllers.User;

import com.memory.Models.Model;
import javafx.beans.InvalidationListener;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.memory.Views.UserMenuItems.HOME;

public class UserController implements Initializable {
    public BorderPane user_parent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getUserMenuItems().addListener((observableVal, oldVal, newVal) ->
        {
            if (Objects.requireNonNull(newVal) == HOME) {
                user_parent.setCenter(Model.getInstance().getViewFactory().getHomeView());
            }
        });
    }
}

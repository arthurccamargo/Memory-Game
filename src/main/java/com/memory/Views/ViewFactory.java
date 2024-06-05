package com.memory.Views;

import com.memory.Controllers.User.UserController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {
    private final ObjectProperty<UserMenuItems> userMenuItems;
    private AnchorPane homeView;
    private AnchorPane gameModeView;

    public ViewFactory() {
        this.userMenuItems = new SimpleObjectProperty<>();
    }


    public ObjectProperty<UserMenuItems> getUserMenuItems() {return userMenuItems;}


    public AnchorPane getGameModeView() {
        if (gameModeView == null) {
            try {
                gameModeView = new FXMLLoader(getClass().getResource("/Fxml/Game/GameMode.fxml")).load();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return gameModeView;
    }

    public AnchorPane getHomeView() {
        if(homeView == null) {
            try {
                homeView = new FXMLLoader(getClass().getResource("/Fxml/User/Home.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return homeView;
    }

    public void showMenuView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/User/Menu.fxml"));
        UserController userController = new UserController();
        loader.setController(userController);
        createStage(loader);
    }

    public void showLoginView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    // Utility Methods
    public void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
             scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Memory Game");
        stage.setResizable(false);
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}

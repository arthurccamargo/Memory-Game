package com.memory.Views;

import com.memory.Controllers.User.UserController;
import com.memory.Models.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ViewFactory {
    private final ObjectProperty<UserMenuItems> userMenuItems;
    private AnchorPane homeView;
    private AnchorPane gameModeView;
    private AnchorPane gameEasyView;


    public ViewFactory() {
        this.userMenuItems = new SimpleObjectProperty<>();
    }

    public ObjectProperty<UserMenuItems> getUserMenuItems() {return userMenuItems;}

    // Get Views Section
    public AnchorPane getGameEasyView() {
        if (gameEasyView == null) {
            try {
                gameEasyView = new FXMLLoader(getClass().getResource("/Fxml/Game/GameEasy.fxml")).load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return gameEasyView;
    }

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

    // Show Views Section
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

    // Alert Method
    public void showAlert(UserMenuItems item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("If you change windows you will lose your game");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            resetBoard();
            getUserMenuItems().set(item);
         }
    }

    public void resetBoard() {
        // starGame = 0 to reset variables and start timer
        Model.getInstance().setStartGame(0);
        // random pieces
        Model.getInstance().getBoard().randomPieces();
        // reset color
        GridPane grid = (GridPane) gameEasyView.getChildren().get(1);
        for (int i = 0; i < (grid.getRowCount() * grid.getColumnCount()); i++) {
            grid.getChildren().get(i).setStyle("-fx-background-color: gray");
            grid.getChildren().get(i).setDisable(false);
        }
        // stop time
        Model.getInstance().getGameTimer().stop();
        // reset labels in GameEasy
        Label time_lbl = (Label) gameEasyView.getChildren().get(3);
        time_lbl.setText("00:00");
        Model.getInstance().getGameTimer().setMinutes(0);
        Model.getInstance().getGameTimer().setSeconds(0);
        Label success_lbl = (Label) gameEasyView.getChildren().get(4);
        success_lbl.setText("0/" + (grid.getRowCount() * grid.getColumnCount())/2);
        Label attempts_lbl = (Label) gameEasyView.getChildren().get(6);
        attempts_lbl.setText("0");
    }

    public void showAlertLogout(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to logout of your account");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Model.getInstance().getViewFactory().getUserMenuItems().set(UserMenuItems.HOME);
            resetBoard();
            closeStage(stage);
            showLoginView();
        }
    }

    public void showAlertWinner() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Winner, do you want to play again?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            resetBoard();
        } else {
            Model.getInstance().getViewFactory().getUserMenuItems().set(UserMenuItems.HOME);
        }
    }
}

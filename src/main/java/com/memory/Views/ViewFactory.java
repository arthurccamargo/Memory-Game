package com.memory.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {

    public void showLoginView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

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
}

package com.memory.Controllers.Game;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameEasyController implements Initializable {
    public GridPane gridPane;
    public Label time_lbl;
    public Button[][] buttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttons = new Button[gridPane.getRowCount()][gridPane.getColumnCount()];
        createButtons();
    }

    public void createButtons() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                buttons[i][j] = new Button();
                gridPane.add(buttons[i][j], j, i);
                buttons[i][j].setMinWidth(117.6);
                buttons[i][j].setMinHeight(68);
            }
        }
    }


}

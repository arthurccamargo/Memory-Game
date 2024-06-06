package com.memory.Controllers.Game;

import com.memory.Models.Board;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameEasyController implements Initializable {
    public GridPane gridPane;
    public Label time_lbl;
    public Button[][] buttons;
    public Board board;
    public List<Color> colorList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttons = new Button[gridPane.getRowCount()][gridPane.getColumnCount()];
        this.board = new Board(gridPane.getRowCount(), gridPane.getColumnCount());
        this.colorList = new ArrayList<>();
        board.createPieces();
        fillListColor();
        createButtons();
    }

    public void fillListColor() {
        for (Color color: Color.values()) {
            colorList.add(color);
        }
    }

    public void createButtons() {
        int index = 0;
        String color;
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                buttons[i][j] = new Button();
                gridPane.add(buttons[i][j], j, i);
                buttons[i][j].setMinWidth(117.6);
                buttons[i][j].setMinHeight(68);
                buttons[i][j].setText("?");

                index = board.getPieces()[i][j].getIdPiece();
                color = colorList.get(index).toString();
                buttons[i][j].setStyle("-fx-background-color:" + color);
            }
        }
    }
}

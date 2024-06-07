package com.memory.Controllers.Game;

import com.memory.Models.Board;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;


public class GameEasyController implements Initializable {
    public GridPane gridPane;
    public Label time_lbl;
    public Button[][] buttons;
    public List<Button> active_buttons;
    public Board board;
    public List<Color> colorList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttons = new Button[gridPane.getRowCount()][gridPane.getColumnCount()];
        this.board = new Board(gridPane.getRowCount(), gridPane.getColumnCount());
        this.active_buttons = new ArrayList<>();
        this.colorList = new ArrayList<>();
        board.createPieces();
        fillListColor();
        createButtons();
        userAction();
    }

    public void fillListColor() {
        for (Color color : Color.values()) {
            colorList.add(color);
        }
    }

    public void userAction() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnAction(event -> onButtons(buttons[finalI][finalJ], finalI, finalJ));
            }
        }
    }

    public void onButtons(Button button, int i, int j) {
        setCor(button, i, j);
        addActiveButton(button);
        if (active_buttons.size() > 1) {
            comparisonButtons();
        }
    }

    public void addActiveButton(Button button) {
        active_buttons.add(button);
    }

    public void removeButtons() {
        active_buttons.clear();
    }

    public void setCor(Button button, int i, int j) {
        int index;
        String color;
        index = board.getPieces()[i][j].getIdPiece();
        color = colorList.get(index).toString();
        button.setStyle("-fx-background-color:" + color);
    }

    public void comparisonButtons() {
        int piece1_id = capturaId(0);
        int piece2_id = capturaId(1);

        if (piece1_id == piece2_id) {
            System.out.println("Sao iguais");
            active_buttons.forEach(button -> button.setDisable(true));
        } else {
            System.out.println("diferentes");
            active_buttons.forEach(button -> button.setDisable(false));
            active_buttons.forEach(button -> button.setStyle("-fx-background-color: gray"));
        }
        removeButtons();
    }

    public int capturaId(int index) {
        // get the name string (button id) to know its position in the matrix
        char second_char = active_buttons.get(index).getId().charAt(1);
        char third_char = active_buttons.get(index).getId().charAt(2);

        int row = Integer.parseInt(String.valueOf(second_char));
        int col = Integer.parseInt(String.valueOf(third_char));
        // return piece id
        return board.getPieces()[row][col].getIdPiece();
    }

    public void createButtons() {
            for (int i = 0; i < gridPane.getRowCount(); i++) {
                for (int j = 0; j < gridPane.getColumnCount(); j++) {
                    buttons[i][j] = new Button();
                    gridPane.add(buttons[i][j], j, i);
                    buttons[i][j].setId("b" + i + j + "_btn");
                    buttons[i][j].setMinWidth(117.6);
                    buttons[i][j].setMinHeight(68);
                    buttons[i][j].setText("?");
                    buttons[i][j].setStyle("-fx-background-color: gray");
                }
            }
    }
}

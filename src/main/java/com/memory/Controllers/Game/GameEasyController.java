package com.memory.Controllers.Game;

import com.memory.Models.Board;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;


public class GameEasyController implements Initializable {
    public GridPane gridPane;
    public Label time_lbl;
    private Button[][] buttons;
    private List<Button> active_buttons;
    private Board board;
    private List<Color> colorList;
    private static final int DELAY = 1000; // ms
    private boolean permission = true; // when comparison between two pieces is allowed - after delay

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

    private void fillListColor() {
        for (Color color : Color.values()) {
            colorList.add(color);
        }
    }

    private void createButtons() {
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

    private void userAction() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnAction(event -> onButtons(buttons[finalI][finalJ], finalI, finalJ));
            }
        }
    }

   private void onButtons(Button button, int i, int j) {
        if (active_buttons.size() < 2) {
            setCor(button, i, j);
            addActiveButton(button);
        }
        if(active_buttons.size() == 2 && permission) {
            permission = false;
            waitComparison();
        }
    }

    private void setCor(Button button, int i, int j) {
        int index;
        String color;
        index = board.getPieces()[i][j].getIdPiece();
        color = colorList.get(index).toString();
        button.setStyle("-fx-background-color:" + color);
    }

    private void addActiveButton(Button button) {
        if (active_buttons.isEmpty() || button != active_buttons.get(0)) {
            active_buttons.add(button);
        }
    }

    private void removeButtons() {
        active_buttons.clear();
    }

    private void waitComparison() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    timer.cancel();
                    comparisonButtons();
                });
            }
        }, DELAY);
    }

    private void comparisonButtons() {
        int piece1_id = captureId(0);
        int piece2_id = captureId(1);
        if (piece1_id == piece2_id) {
            active_buttons.forEach(button -> button.setDisable(true));
        } else {
            active_buttons.forEach(button -> button.setDisable(false));
            active_buttons.forEach(button -> button.setStyle("-fx-background-color: gray"));
        }
        permission = true;
        removeButtons();
    }

    private int captureId(int index) {
        // get the name string (button id) to know its position in the matrix
        char second_char = active_buttons.get(index).getId().charAt(1);
        char third_char = active_buttons.get(index).getId().charAt(2);

        int row = Integer.parseInt(String.valueOf(second_char));
        int col = Integer.parseInt(String.valueOf(third_char));
        // return piece id
        return board.getPieces()[row][col].getIdPiece();
    }
}

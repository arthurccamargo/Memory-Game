package com.memory.Controllers.Game;

import com.memory.Models.Model;
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
    public Label success_lbl;
    public Label attempts_lbl;
    private Button[][] buttons;
    private List<Button> active_buttons;
    private List<Color> colorList;
    private static final int DELAY = 1000; // ms
    private boolean comparison_permission = true; // when comparison between two pieces is allowed - after delay
    private int success_count = 0;
    private int attempts =0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttons = new Button[gridPane.getRowCount()][gridPane.getColumnCount()];
        this.active_buttons = new ArrayList<>();
        this.colorList = new ArrayList<>();
        Model.getInstance().getBoard().createPieces();
        Model.getInstance().getBoard().randomPieces();
        setTextSuccess();
        fillListColor();
        createButtons();
        userAction();
    }

    public void setTextSuccess() {
        success_lbl.setText(success_count + "/" + (gridPane.getRowCount() * gridPane.getColumnCount())/2);
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
                buttons[i][j].setOnAction(event -> onButtons(buttons[finalI][finalJ]));
            }
        }
    }

    private void onButtons(Button button) {
        // the game is starting
        if (Model.getInstance().getStartGame() == 0) {
            // start Timer
            Model.getInstance().getGameTimer().start(time_lbl);
            // set startGame = 1;
            Model.getInstance().setStartGame(1);
            // reset variables to labels
            attempts=0;
            success_count=0;
        }
        if (active_buttons.size() < 2) {
            setCor(button);
            addActiveButton(button);
        }
        if (active_buttons.size() == 2 && comparison_permission) {
            comparison_permission = false;
            waitComparison();
        }
    }

    private void setCor(Button button) {
        int index;
        String color;
        int row = rowButton(button);
        int col = colButton(button);
        index = Model.getInstance().getBoard().getPieces()[row][col].getIdPiece();
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
        Timer timerWait = new Timer();
        timerWait.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    timerWait.cancel();
                    comparisonButtons();
                });
            }
        }, DELAY);
    }

    private void comparisonButtons() {
        // set user attempts
        attempts++;
        Model.getInstance().getUser().setAttempts(attempts);
        attempts_lbl.setText(String.valueOf(attempts));

        int piece1_id = captureId(0);
        int piece2_id = captureId(1);
        if (piece1_id == piece2_id) {
            active_buttons.forEach(button -> button.setDisable(true));
            success_count++;
            success_lbl.setText(success_count + "/" + (gridPane.getRowCount() * gridPane.getColumnCount())/2);
            // player win
            if (success_count == (gridPane.getRowCount() * gridPane.getColumnCount())/2) {
                winner();
            }
        } else {
            active_buttons.forEach(button -> button.setDisable(false));
            active_buttons.forEach(button -> button.setStyle("-fx-background-color: gray"));
        }
        comparison_permission = true;
        removeButtons();
    }

    private int captureId(int index) {
        // get the button of active buttons
        Button button = active_buttons.get(index);
        int row = rowButton(button);
        int col = colButton(button);
        // return piece id
        return Model.getInstance().getBoard().getPieces()[row][col].getIdPiece();
    }

    private void winner() {
        winnerTime();
        // stop time
        Model.getInstance().getGameTimer().stop();
        // show alert winner
        Model.getInstance().getViewFactory().showAlertWinner();
        // reset labels
        time_lbl.setText("00:00");
        Model.getInstance().getGameTimer().setMinutes(0);
        Model.getInstance().getGameTimer().setSeconds(0);
        attempts=0;
        attempts_lbl.setText("0");
        success_count=0;
        setTextSuccess();
        // starGame = 0 to start timer at the beginning of the game
        Model.getInstance().setStartGame(0);
    }

    private void winnerTime() {
        // get user time
        String userTime = Model.getInstance().getUser().getTimeEasyGame();
        if (Objects.equals(userTime, "--:--")) {
            // set user time
            Model.getInstance().getUser().setTimeEasyGame(time_lbl.getText());
            // get user id
            int id =Model.getInstance().getUser().getId();
            // create user time data
            Model.getInstance().getDatabaseDriver().createUserTime(id, time_lbl.getText());
        }
    }

    // get the position button
    private int rowButton(Button button) {
        int row;
        Iterator<Object> list = button.getProperties().values().iterator();
        row = (int) list.next();
        row = (int) list.next();
        return row;
    }

    private int colButton(Button button) {
        int col;
        Iterator<Object> list = button.getProperties().values().iterator();
        col = (int) list.next();
        return col;
    }
}

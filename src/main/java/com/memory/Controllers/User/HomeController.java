package com.memory.Controllers.User;

import com.memory.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Label hAttemptsEasy_lbl;
    public Label bTimeEasy_lbl;
    public Label hAttemptsMedium_lbl;
    public Label bTimeMedium_lbl;
    public Label hAttemptsHard_lbl;
    public Label bTimeHard_lbl;
    public Label username_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_lbl.setText("Hi, " + Model.getInstance().getUser().getName());
        // user game time data
        Model.getInstance().userTimes(Model.getInstance().getUser().getId());
        bTimeEasy_lbl.setText(Model.getInstance().getUser().getTimeEasyGame());
        bTimeMedium_lbl.setText("--:--");
        bTimeHard_lbl.setText("--:--");
        // user game score data
        Model.getInstance().userAttempts(Model.getInstance().getUser().getId());
        hAttemptsEasy_lbl.setText(Model.getInstance().getUser().getAttemptsEasyGame());
        hAttemptsMedium_lbl.setText("-");
        hAttemptsHard_lbl.setText("-");
    }
}

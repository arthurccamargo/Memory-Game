package com.memory.Controllers.User;

import com.memory.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Label hScoreEasy_lbl;
    public Label bTimeEasy_lbl;
    public Label hScoreMedium_lbl;
    public Label bTimeMedium_lbl;
    public Label hScoreHard_lbl;
    public Label bTimeHard_lbl;
    public Label username_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_lbl.setText("Hi, " + Model.getInstance().getUser().getName());
    }
}

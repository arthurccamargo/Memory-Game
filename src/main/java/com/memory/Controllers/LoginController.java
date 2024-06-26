package com.memory.Controllers;

import com.memory.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public PasswordField password_fld;
    public TextField username_fld;
    public Button login_btn;
    public Label error_lbl;
    public Button createUser_btn;
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // if button is clicked
        login_btn.setOnAction(e -> onLogin());
        createUser_btn.setOnAction(e -> onCreateUser());
        displayImage();
    }

    private void onLogin() {
        // get stage
        Stage stage = (Stage) login_btn.getScene().getWindow();
        // evaluate User Login Credentials
        Model.getInstance().evaluateUserCred(username_fld.getText(), password_fld.getText());
        if (Model.getInstance().getUserLoginSuccessFlag()) {
            // show menu window
            Model.getInstance().getViewFactory().showMenuView();
            // close stage the login window
            Model.getInstance().getViewFactory().closeStage(stage);
        } else {
            error_lbl.setText("Invalid Username or Password");
        }
        username_fld.setText("");
        password_fld.setText("");
    }

    private void onCreateUser() {
        // get stage
        Stage stage = (Stage) createUser_btn.getScene().getWindow();
        // close stage the login window
        Model.getInstance().getViewFactory().closeStage(stage);
        // show create user window
        Model.getInstance().getViewFactory().showCreateUserView();
    }

    public void displayImage() {
        Image image = new Image(getClass().getResourceAsStream("/Images/login.jpg"));
        imageView.setImage(image);
    }
}
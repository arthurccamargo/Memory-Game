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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    public TextField createUserName_fld;
    public PasswordField createUserPassword_fld;
    public Button createNewUser_btn;
    public Label error_lbl;
    public Button back_btn;
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createNewUser_btn.setOnAction(e -> onCreateNewUser());
        back_btn.setOnAction(e -> onBack());
        displayImage();
    }

    private void onCreateNewUser() {
        String username = createUserName_fld.getText();
        String password = createUserPassword_fld.getText();
        // If the user already exists, it is not possible to create user
        if (!Model.getInstance().userExists(username)) {
            // create user
            Model.getInstance().getDatabaseDriver().createUser(username, password, LocalDate.now());
            // get stage
            Stage stage = (Stage) createNewUser_btn.getScene().getWindow();
            // close stage the create user window
            Model.getInstance().getViewFactory().closeStage(stage);
            // show login window
            Model.getInstance().getViewFactory().showLoginView();
        } else {
            error_lbl.setText("The username already exists");
        }
        emptyFields();
    }


    private void emptyFields() {
        createUserName_fld.setText("");
        createUserPassword_fld.setText("");
    }

    private void onBack() {
        // get stage
        Stage stage = (Stage) back_btn.getScene().getWindow();
        // close stage the create user window
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginView();
    }

    public void displayImage() {
        Image image = new Image(getClass().getResourceAsStream("/Images/createUser.jpg"));
        imageView.setImage(image);
    }
}

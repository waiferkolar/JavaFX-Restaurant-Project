package coder.controllers;

import coder.Main;
import coder.helper.MyDialog;
import coder.models.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button tvLoginBtn;

    @FXML
    private TextField tvPassword;

    @FXML
    private TextField tvUsername;

    @FXML
    void startLogin(ActionEvent event) throws IOException {
        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();

        UserService userService = new UserService();
        if (userService.loginUser(username, password)) {
            GridPane home_panel = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
            Main.getBorderPane().setCenter(home_panel);
        } else {
            MyDialog.show("Username or Password Error! Try again!");
        }
    }

}

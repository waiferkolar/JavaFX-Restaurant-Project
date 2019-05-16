package coder.controllers;

import coder.Main;
import coder.helper.MyDialog;
import coder.models.User;
import coder.models.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserCreateController implements Initializable {

    @FXML
    private ChoiceBox<String> admin_cu_role;

    @FXML
    private ChoiceBox<String> admin_cu_enabled;

    @FXML
    private TextField admin_cu_username;

    @FXML
    private TextField admin_cu_password;

    User user = Main.user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        admin_cu_role.getItems().addAll("1", "2");
        admin_cu_role.getSelectionModel().select(0);
        admin_cu_enabled.getItems().addAll("0", "1");
        admin_cu_enabled.getSelectionModel().select(1);

        if (user.getId() != 0) {
            admin_cu_username.setText(user.getUsername());
            admin_cu_password.setText(user.getPassword());
            if (user.isEnabled()) {
                admin_cu_enabled.getSelectionModel().select(1);
            } else {
                admin_cu_enabled.getSelectionModel().select(0);
            }
            if (user.getRole() == 1) {
                admin_cu_role.getSelectionModel().select(0);
            } else {
                admin_cu_role.getSelectionModel().select(1);
            }

        }

    }

    @FXML
    void startCreateUser(ActionEvent event) {

        user.setUsername(admin_cu_username.getText());
        user.setPassword(admin_cu_password.getText());

        if (admin_cu_enabled.getSelectionModel().getSelectedItem().contentEquals("0")) {
            user.setEnabled(false);
        } else {
            user.setEnabled(true);
        }
        user.setRole(Integer.parseInt(admin_cu_role.getSelectionModel().getSelectedItem()));

        UserService userService = new UserService();

        if (user.getId() != 0) {
            if (userService.updateUser(user)) {
                try {
                    VBox admin_user_panel = FXMLLoader.load(getClass().getResource("../views/admin_users.fxml"));
                    Main.getBorderPane().setCenter(admin_user_panel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                MyDialog.show("Something Wrong!");
            }
        } else {
            if (userService.saveUser(user)) {
                try {
                    VBox admin_user_panel = FXMLLoader.load(getClass().getResource("../views/admin_users.fxml"));
                    Main.getBorderPane().setCenter(admin_user_panel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                MyDialog.show("Something Wrong!");
            }
        }


    }
}

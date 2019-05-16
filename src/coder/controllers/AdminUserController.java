package coder.controllers;


import coder.Main;
import coder.models.User;
import coder.models.services.UserService;
import com.apple.eawt.AppEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminUserController implements Initializable {

    @FXML
    private TableColumn<User, String> admin_user_name;

    @FXML
    private TableColumn<User, Integer> admin_user_id;

    @FXML
    private TableColumn<User, Integer> admin_user_role;

    @FXML
    private TableColumn<User, String> admin_user_password;

    @FXML
    private TableColumn<User, Boolean> admin_user_enabled;

    @FXML
    private TableView<User> admin_all_users_table;

    ObservableList<User> obUsers = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin_user_name.setCellValueFactory(new PropertyValueFactory<>("username"));
        admin_user_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        admin_user_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        admin_user_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        admin_user_enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));

        UserService userService = new UserService();
        List<User> users = userService.getAllUser();

        for (User user : users) {
            obUsers.add(user);
        }

        admin_all_users_table.getItems().addAll(obUsers);
        addDeleteColumn("Delete", "delete");
        addDeleteColumn("Edit", "edit");
    }

    public void addDeleteColumn(String columnName, String buttonName) {
        TableColumn<User, Void> actionColumn = new TableColumn<>(columnName);

        Callback<TableColumn<User, Void>, TableCell<User, Void>> factory =
                new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {

                    @Override
                    public TableCell<User, Void> call(TableColumn<User, Void> param) {
                        final TableCell<User, Void> cell = new TableCell<User, Void>() {
                            final Button button = new Button(buttonName);

                            {
                                button.setOnAction(e -> {
                                    int ind = admin_all_users_table.getSelectionModel().getFocusedIndex();
                                    if (ind != -1) {
                                        Main.user = obUsers.get(ind);
                                        if (buttonName.contentEquals("edit")) {
                                            try {
                                                AnchorPane user_create_panel  = FXMLLoader.load(getClass().getResource("../views/user_create.fxml"));
                                                Main.getBorderPane().setCenter(user_create_panel);
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }

                                        } else {
                                            UserService service = new UserService();
                                            service.deleteUser(obUsers.get(ind).getId());
                                            try {
                                                VBox admin_user_panel = FXMLLoader.load(getClass().getResource("../views/admin_users.fxml"));
                                                Main.getBorderPane().setCenter(admin_user_panel);
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }

                                        }
                                    }
                                });
                            }

                            @Override
                            protected void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(button);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionColumn.setCellFactory(factory);
        admin_all_users_table.getColumns().add(actionColumn);
    }

    @FXML
    void createUser(ActionEvent event) throws IOException {
        AnchorPane user_create_panel = FXMLLoader.load(getClass().getResource("../views/user_create.fxml"));
        Main.getBorderPane().setCenter(user_create_panel);
    }
}